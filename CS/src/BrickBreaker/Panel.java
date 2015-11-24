/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BrickBreaker;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Panel extends JPanel {

    public boolean levelPassed = false;
    public boolean testMode = false;
    public int level = 0;
    public boolean poisoned;
    public int GreenPowderParticles = 70;
    public boolean Stunned;
    public int paddlesLeft = 3;
    public int RedPowderParticles = 100;
    public int randomLength = 0;
    public double paddleControl = 1;
    public double powerUpDropProbability = 0.4;
    public int ballCount = 1;
    public double goodPowerUpDropProbability = 0.2;
    public Rectangle intersect;
    public Rectangle intersection;
    public Brick brick;
    public int mouseSensitivity = 1;
    public boolean mirrorEffect = false;
    public int drift = 0;
    public double driftProbability = 0;
    public Random random = new Random();
    public int pwidth = 100;
    public int pheight = 10;
    public int px = (Test.FRAMEWIDTH / 2) - (pwidth / 2);
    public int py = (Test.FRAMEHEIGHT - 70);
    public Ball ball = new Ball(px + pwidth / 2, py - 10, 10, 10);
    public Paddle paddle = new Paddle(px, py, pwidth, pheight);
    public ArrayList<Ball> balls = new ArrayList();
    public ArrayList<Brick> bricks = new ArrayList();
    public ArrayList<PowerUps> powups = new ArrayList();
    public int number = 0;
    JLabel label = new JLabel("PAUSED");

    public Panel(int level) {
        this.level = level;
        nextLevel(level);
        BufferedImage hideCursor = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Cursor InvisibleCursor = Toolkit.getDefaultToolkit().createCustomCursor(hideCursor, new Point(0, 0), "");
        setCursor(InvisibleCursor);
        addMouseMotionListener(new MovePaddle());
        ball.isStatic = true;
        balls.add(ball);
        addMouseListener(new launchBall());
        addKeyListener(new FieldListener());
        setFocusable(true);
    }

    public void paint(Graphics g) {
        super.paint(g);
        balls.stream().forEach((ball1) -> {
            ball1.drawOn(g);
        });
        paddle.drawOn(g);
        for (Brick brick1 : bricks) {
            if (!brick1.remove) {
                brick1.brick.paintIcon(this, g, brick1.x1, brick1.y1);
            }
        }
        for (PowerUps powup : powups) {
            if (powup.dropped && !(powup instanceof RedPowder || powup instanceof GreenPowder)) {
                powup.icon.paintIcon(this, g, powup.x1, powup.y1);
            } else if (powup.dropped && (powup instanceof RedPowder || powup instanceof GreenPowder)) {
                powup.drawOn(g);
            }
        }
    }

    public void ballSpeed() {
        for (Ball ball1 : balls) {
            if (!ball1.isStatic) {
                if (ball1.xincreasing && ball1.yincreasing) {
                    ball1.x1 += ball1.xspeed + drift(ball1.drift, ball1.driftProbability);
                    ball1.y1 += ball1.yspeed + drift(ball1.drift, ball1.driftProbability);
                    ball1.setLocation(ball1.x1, ball1.y1);
                    repaint();
                } else if (ball1.xincreasing && !ball1.yincreasing) {
                    ball1.x1 += ball1.xspeed + drift(ball1.drift, ball1.driftProbability);
                    ball1.y1 -= ball1.yspeed + drift(ball1.drift, ball1.driftProbability);
                    ball1.setLocation(ball1.x1, ball1.y1);
                    repaint();
                } else if (!ball1.xincreasing && !ball1.yincreasing) {
                    ball1.x1 -= ball1.xspeed + drift(ball1.drift, ball1.driftProbability);
                    ball1.y1 -= ball1.yspeed + drift(ball1.drift, ball1.driftProbability);
                    ball1.setLocation(ball1.x1, ball1.y1);
                    repaint();
                } else if (!ball1.xincreasing && ball1.yincreasing) {
                    ball1.x1 -= ball1.xspeed + drift(ball1.drift, ball1.driftProbability);
                    ball1.y1 += ball1.yspeed + drift(ball1.drift, ball1.driftProbability);
                    ball1.setLocation(ball1.x1, ball1.y1);
                    repaint();
                }
            }
        }
    }

    class launchBall implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent me) {
            if (ball.isStatic = false) {
                ball.isStatic = true;
            }
        }

        @Override
        public void mousePressed(MouseEvent me) {

        }

        @Override
        public void mouseReleased(MouseEvent me) {

        }

        @Override
        public void mouseEntered(MouseEvent me) {

        }

        @Override
        public void mouseExited(MouseEvent me) {

        }

    }

    class MovePaddle implements MouseMotionListener {

        @Override
        public void mouseDragged(MouseEvent me) {

        }

        @Override
        public void mouseMoved(MouseEvent me) {
            if (testMode) {
                ball.x1 = me.getX();
                ball.y1 = me.getY();
                ball.setLocation(ball.x1, ball.y1);
            }
            if (mirrorEffect == true && (Test.FRAMEWIDTH > me.getX() + pwidth + 15) && !Stunned) {
                if (ball.isStatic) {
                    ball.x1 = me.getX() + pwidth / 2;
                }
                if (random.nextDouble() < paddleControl) {
                    paddle.x1 = me.getX() - (2 * (me.getX() - Test.FRAMEWIDTH / 2) + pwidth);
                }
                if (randomLength > 0) {
                    paddle.x2 = random.nextInt(randomLength) + pwidth;
                }
                paddle.setLocation(paddle.x1, paddle.y1);
                repaint();
            } else if (Test.FRAMEWIDTH > mouseSensitivity * me.getX() + pwidth + 15 && !Stunned) {
                if (ball.isStatic) {
                    ball.x1 = me.getX() + pwidth / 2;
                }
                if (random.nextDouble() < paddleControl) {
                    paddle.x1 = (mouseSensitivity * me.getX());
                }
                if (randomLength > 0) {
                    paddle.x2 = random.nextInt(randomLength) + pwidth;
                }
                paddle.setLocation(paddle.x1, paddle.y1);
                repaint();
            }
            if (paddle.x1 + paddle.x2 > Test.FRAMEWIDTH) {
                paddle.x1 = Test.FRAMEWIDTH - (paddle.x2 + 20);
                paddle.setLocation(paddle.x1, paddle.y1);
                repaint();
            }
        }
    }

    public void bounce() {

        //ArrayList<Ball> ballstmp = new ArrayList(balls);
        for (Ball ball1 : balls) {
            if (!(ball1.xincreasing) && ball1.x1 < 0) {
                ball1.xincreasing = true;
                randomizeDirection(ball1);
                speedBalancer(ball1);
            } else if (!ball1.yincreasing && ball1.y1 < 0) {
                ball1.yincreasing = true;
                randomizeDirection(ball1);
                speedBalancer(ball1);
            } else if (ball1.xincreasing && ball1.x1 + 10 > Test.FRAMEWIDTH - 15) {
                ball1.xincreasing = false;
                randomizeDirection(ball1);
                speedBalancer(ball1);
            } else if (ball1.getBounds().intersects(paddle.getBounds())) {
                if (paddle.sticky) {
                    ball1.isStatic = true;
                } else {
                    ball1.yincreasing = false;
                    randomizeDirection(ball1);
                    speedBalancer(ball1);
                }
            } else if (ball1.y1 > Test.FRAMEHEIGHT) {
                ballCount--;
                if (ballCount == 0) {
                    restart();
                }
            } else {
                for (Brick brick1 : bricks) {
                    if (!brick1.remove) {
                        intersect = brick1.getBounds();
                        if (ball1.intersects(intersect)) {
                            intersection = ball1.getBounds().intersection(brick1.getBounds());
                            if (intersection.width >= intersection.height) {
                                if (ball1.yincreasing) {
                                    ball1.yincreasing = false;
                                    brick1.strength--;
                                    if (brick1.strength == 0) {
                                        brick1.remove = true;
                                        number--;
                                        randomPowerup(ball.x1, ball.y1);
                                    }
                                    randomizeDirection(ball1);
                                    speedBalancer(ball1);

                                } else if (!ball1.yincreasing) {
                                    ball1.yincreasing = true;
                                    brick1.strength--;
                                    if (brick1.strength == 0) {
                                        brick1.remove = true;
                                        number--;
                                        randomPowerup(ball.x1, ball.y1);
                                    }
                                    randomizeDirection(ball1);
                                    speedBalancer(ball1);

                                }
                            } else if (intersection.height >= intersection.width) {
                                if (ball1.xincreasing) {
                                    ball1.xincreasing = false;
                                    brick1.strength--;
                                    if (brick1.strength == 0) {
                                        brick1.remove = true;
                                        number--;
                                        randomPowerup(ball.x1, ball.y1);
                                    }
                                    randomizeDirection(ball1);
                                    speedBalancer(ball1);

                                } else if (!ball1.yincreasing) {
                                    ball1.xincreasing = true;
                                    brick1.strength--;
                                    if (brick1.strength == 0) {
                                        brick1.remove = true;
                                        number--;
                                        randomPowerup(ball.x1, ball.y1);
                                    }
                                    randomizeDirection(ball1);
                                    speedBalancer(ball1);

                                }
                            }
                        }
                    }
                }
                if (number == 0) {
                    levelPassed = true;
                }
            }
            for (PowerUps powup1 : powups) {
                if (powup1.y1 >= Test.FRAMEHEIGHT) {
                    powup1.dropped = false;
                } else if (powup1.dropped && powup1.getBounds().intersects(paddle.getBounds())) {
                    /*
                     if(powup.instance==1){
                     // addBall(1,ball.x1,ball.y1);//Bu metodu koymayınca sorun olmuyor ama top da eklenmiyor.
                     powup.dropped=false;
                     if(numOfPowups==0)powerUpsOnBoard = false;
                     }
                     */
                    if (powup1.instance == 2) {
                        expandPaddle(20);
                        powup1.dropped = false;
                    } else if (powup1.instance == 3) {
                        shrinkPaddle(30);
                        powup1.dropped = false;
                    } else if (powup1.instance == 4) {
                        for (Ball ball2 : balls) {
                            ball2.xspeed += 4;
                            ball2.yspeed += 4;
                            ball2.equilibriumSpeed = ball2.equilibriumSpeed + 4;
                        }
                        powup1.dropped = false;
                    } else if (powup1.instance == 5) {
                        for (Ball ball3 : balls) {
                            if (ball3.equilibriumSpeed > 3) {
                                ball3.equilibriumSpeed -= 2;
                            }
                        }
                        powup1.dropped = false;
                    } else if (powup1.instance == 6) {
                        for (Ball ball4 : balls) {
                            if (ball4.equilibriumSpeedIncrementProbability > 0.01) {
                                ball4.equilibriumSpeedIncrementProbability -= 0.01;
                            }
                        }
                        powup1.dropped = false;
                    } else if (powup1.instance == 7) {
                        for (Ball ball5 : balls) {
                            ball5.equilibriumSpeedIncrementProbability += 0.02;
                        }
                        powup1.dropped = false;
                    } else if (powup1.instance == 8) {
                        for (Ball ball6 : balls) {
                            if (ball6.drift == 0 || ball6.driftProbability == 1) {
                                ball6.drift += 10;
                            }
                            if (ball6.driftProbability < 1) {
                                ball6.driftProbability += 0.3;
                                if (ball6.driftProbability > 1) {
                                    ball6.driftProbability = 1;
                                }
                            }
                            powup1.dropped = false;
                        }
                    } else if (powup1.instance == 9) {
                        mirrorEffect = true;
                        powup1.dropped = false;
                    } else if (powup1.instance == 10) {
                        double k = random.nextDouble();
                        if (k >= 0 && k <= 0.20) {
                            shrinkPaddle(1);
                        } else if (k >= 0.20 && k <= 0.44) {
                            paddleControl -= 0.01;

                        } else if (k >= 0.44 && k <= 0.69) {
                            for (int i = 0; i < balls.size(); i++) {
                                balls.get(i).equilibriumSpeedIncrementProbability += 0.001;
                            }
                        } else if (k >= 0.69 && k <= 0.79) {
                            if (goodPowerUpDropProbability > 0.01) {
                                goodPowerUpDropProbability -= 0.001;
                            }
                        } else if (k >= 0.79 && k <= 0.89) {
                            RedPowderParticles += 5;
                        } else if (k >= 0.89 && k <= 0.94) {
                            shrinkPaddle(2);
                            pwidth = paddle.x2;
                            randomLength += 2;
                        } else if (k >= 0.94 && k < 0.97) {
                            StunPaddle stun = new StunPaddle(random.nextInt(2000));
                            stun.run();
                        } else if (k >= 0.97 && k <= 1) {
                            Poisoned poisoned = new Poisoned(25);
                            System.out.println("Poisoned");//
                            poisoned.start();
                        }
                        powup1.dropped = false;
                    } else if (powup1.instance == 11) {
                        goodPowerUpDropProbability += 0.03;
                        powup1.dropped = false;
                    } else if (powup1.instance == 12) {
                        paddle.sticky = true;
                        powup1.dropped = false;
                    } else if (powup1 instanceof GreenPowder) {
                        if (poisoned && random.nextInt() <= 0.2) {
                            poisoned = false;
                        } else {
                            double k = random.nextDouble();
                            if (k >= 0 && k <= 0.20) {
                                expandPaddle(1);
                            } else if (k >= 0.20 && k <= 0.44) {
                                paddleControl += 0.01;

                            } else if (k >= 0.44 && k <= 0.69) {
                                for (int i = 0; i < balls.size(); i++) {
                                    balls.get(i).equilibriumSpeedIncrementProbability -= 0.001;
                                }
                            } else if (k >= 0.69 && k <= 0.79) {
                                if (goodPowerUpDropProbability > 0.01) {
                                    goodPowerUpDropProbability += 0.001;
                                }
                            } else if (k >= 0.79 && k <= 0.89) {
                                GreenPowderParticles += 5;
                            } else if (k >= 0.89 && k <= 1) {
                                if (randomLength > 2) {
                                    expandPaddle(2);
                                }
                                pwidth = paddle.x2;
                                randomLength -= 2;
                            }
                            powup1.dropped = false;
                        }
                    }
                }
            }
            for (PowerUps powup : powups) {
                if (powup.x1 + 50 > Test.FRAMEWIDTH) {
                    powup.xdirection = -1;
                }
                if (powup.x1 < 0) {
                    powup.xdirection = 1;
                }
                if (powup.y1 < 0) {
                    powup.ydirection = 1;
                }
            }
        }
    }

    public int drift(int a, double p) {
        if (p != 0 && random.nextDouble() <= p) {
            return random.nextInt(a);
        }
        return 0;
    }

    public void randomizeDirection(Ball ball) {
        if (random.nextBoolean() && ball.xspeed > 2) {
            ball.xspeed -= random.nextInt((int) ball.xspeed / 2) + 1;
            ball.yspeed += (int) (Math.sqrt(Math.pow(ball.speed, 2) - Math.pow(ball.xspeed, 2)) - ball.yspeed);
            ball.speed = ball.calculateSpeed();
        } else if (ball.yspeed > 2) {
            ball.yspeed -= random.nextInt((int) ball.yspeed / 2) + 1;
            ball.xspeed += (int) (Math.sqrt(Math.pow(ball.speed, 2) - Math.pow(ball.yspeed, 2)) - ball.xspeed);
            ball.speed = ball.calculateSpeed();
        }
    }

    public void speedBalancer(Ball ball) {

        if (ball.equilibriumSpeedIncrementProbability >= random.nextDouble()) {
            ball.equilibriumSpeed++;
        }
        if (ball.equilibriumSpeed > ball.speed) {
            ball.xspeed++;
            ball.yspeed++;
            ball.speed = ball.calculateSpeed();
        } else {
            ball.xspeed--;
            ball.yspeed--;
            ball.speed = ball.calculateSpeed();
        }
    }

    public void expandPaddle(int i) {
        paddle.x2 += i;
        pwidth = paddle.x2;
        paddle.setSize(paddle.x2, paddle.y2);
        repaint();
    }

    public void shrinkPaddle(int i) {
        paddle.x2 -= i;
        pwidth = paddle.x2;
        paddle.setSize(paddle.x2, paddle.y2);
        repaint();
    }

    public void addBall(int i, int x, int y) {

        for (int j = 0; j < i; j++) {
            balls.add(new Ball(x, y, 10, 10));
            //balls.get(j).xspeed = random.nextInt(3)+3;
            //balls.get(j).yspeed = random.nextInt(3)+3;
        }
    }

    public void addBrick(int i, int x, int y, int k) {
        for (int v = 0; v < i; v++) {
            bricks.add(new Brick(x + (v * k * 4), y, 100, 100));
            number++;
        }
    }

    public void addBrick2(int i, int x, int y, int k) {
        for (int v = 0; v < i; v = v + 2) {
            bricks.add(new Brick(x + (v * k * 4), y, 100, 100));
            number++;
        }
    }

    public void addBrick3(int i, int x, int y, int k) {
        for (int v = 1; v < i; v = v + 2) {
            bricks.add(new Brick(x + (v * k * 4), y, 100, 100));
            number++;
        }
    }

    public void addBrick4(int i, int x, int y, int k, int Aras) {
        for (int v = 0; v < i - Aras; v++) {
            bricks.add(new Brick(x + (v * k * 4), y, 100, 100));
            number++;
        }
    }

    public void addBrick5(int i, int x, int y, int k) {
        for (int v = 0; v < i;) {
            Random r = new Random();
            int a = r.nextInt(5);
            v = v + a;
            bricks.add(new Brick(x + (v * k * 4), y, 100, 100));
            number++;
        }
    }
    public void addBrick6(int x, int y, int k) {
		for (int v = 0; v < 16; v++) {
			if (v == 9){
				v++;
			}
			bricks.add(new Brick(x + (v * k * 4), y, 100, 100));
			number++;
		}
	}
    public void addBrick7(int x, int y, int k) {
		for (int v = 0; v < 9; v = v +4) {
			bricks.add(new Brick(x + (v * k * 4), y, 100, 100));
			number++;
		}
		for (int v = 10; v < 11; v++) {
			bricks.add(new Brick(x + (v * k * 4), y, 100, 100));
			number++;
		}
	}
	public void addBrick8(int x, int y, int k) {
		for (int v = 0; v < 9; v = v +4) {
			bricks.add(new Brick(x + (v * k * 4), y, 100, 100));
			number++;
		}
		for (int v = 10; v < 16; v++) {
			bricks.add(new Brick(x + (v * k * 4), y, 100, 100));
			number++;
		}
	}
	public void addBrick9(int x, int y, int k) {
		for (int v = 0; v < 9; v = v +4) {
			bricks.add(new Brick(x + (v * k * 4), y, 100, 100));
			number++;
		}
		for (int v = 15; v < 16; v++) {
			bricks.add(new Brick(x + (v * k * 4), y, 100, 100));
			number++;
		}
	}
	public void addBrick10(int i, int x, int y, int k) {
        for (int v = 0; v < i; v = v+2) {
            bricks.add(new Brick(x + (v * k * 4), y, 100, 100));
            number++;
        }
        for (int v = 1; v < i; v = v+2) {
            bricks.add(new StrongBrick(x + (v * k * 4), y, 100, 100));
            number++;
        }
    }
    public void restart() {
        paddlesLeft--;
        if (paddlesLeft == 0) {
            paddlesLeft = 3;
            for (int i = 0; i < bricks.size(); i++) {
                bricks.get(i).remove = false;
            }
        }
        ball.setPosition(paddle.x1 + pwidth / 2, py - 10, 10, 10);
        ball.isStatic = true;
        ball.reset();
        ballCount = 1;
        paddle.x2 = 100 - randomLength;
        mirrorEffect = false;
        pwidth = paddle.x2;
        paddle.sticky = false;
        paddle.setSize(paddle.x2, paddle.y1);
        for (PowerUps powup : powups) {
            powup.dropped = false;
        }
        repaint();
    }

    public void reset() {
        poisoned = false;
        GreenPowderParticles = 70;
        Stunned = false;
        RedPowderParticles = 100;
        randomLength = 0;
        paddleControl = 1;
        powerUpDropProbability = 0.4;
        ballCount = 1;
        goodPowerUpDropProbability = 0.2;
        mouseSensitivity = 1;
        mirrorEffect = false;
        drift = 0;
        driftProbability = 0;
        pwidth = 100;
        pheight = 10;
        px = (Test.FRAMEWIDTH / 2) - (pwidth / 2);
        py = (Test.FRAMEHEIGHT - 70);
        ball = new Ball(px + pwidth / 2, py - 10, 10, 10);
        paddle = new Paddle(px, py, pwidth, pheight);
        ball.isStatic = true;
        number = 0;
        paddle.setSize(paddle.x2, paddle.y1);
        for (PowerUps powup : powups) {
            powup.dropped = false;
        }
        repaint();
    }

    public void randomPowerup(int x1, int y1) {
        if (powerUpDropProbability >= random.nextDouble()) {
            PowerUps powup;
            boolean x = random.nextBoolean();
            boolean y = random.nextBoolean();
            int xi;
            int yi;
            if (x) {
                xi = 1;
            } else {
                xi = -1;
            }
            if (y) {
                yi = 1;
            } else {
                yi = -1;
            }
            if (goodPowerUpDropProbability >= random.nextDouble()) {
                double k = random.nextDouble();

                if (k >= 0 && k <= 0.30) {
                    powup = new ExpandPaddle(x1, y1, random.nextInt(7) + 1, random.nextInt(7) + 1, xi, yi);
                    powups.add(powup);
                } else if (k >= 0.30 && k <= 0.65) {
                    powup = new SlowDown(x1, y1, random.nextInt(7) + 1, random.nextInt(7) + 1, xi, yi);
                    powups.add(powup);
                } else if (k >= 0.65 && k <= 0.75) {
                    powup = new FasterSlower(x1, y1, random.nextInt(7) + 1, random.nextInt(7) + 1, xi, yi);
                    powups.add(powup);
                } else if (k >= 0.75 && k <= 0.80) {
                    powup = new GoodPowups(x1, y1, random.nextInt(7) + 1, random.nextInt(7) + 1, xi, yi);
                    powups.add(powup);
                } else if (k >= 0.80 && k <= 1) {
                    double numOfParticles = random.nextInt(GreenPowderParticles);
                    for (int i = 0; i < numOfParticles; i++) {
                        boolean x2 = random.nextBoolean();
                        boolean y2 = random.nextBoolean();
                        int xi1;
                        int yi1;
                        if (x2) {
                            xi1 = 1;
                        } else {
                            xi1 = -1;
                        }
                        if (y2) {
                            yi1 = 1;
                        } else {
                            yi1 = -1;
                        }
                        powup = new GreenPowder(x1 + xi1 * random.nextInt(20), y1 + yi1 * random.nextInt(20), random.nextInt(10) + 1, random.nextInt(10) + 1, xi1 * xi, yi1 * yi);
                        powups.add(powup);
                    }
                }
            } else {
                double k = random.nextDouble();
                if (k >= 0 && k <= 0.1) {
                    powup = new ShrinkPaddle(x1, y1, random.nextInt(7) + 1, random.nextInt(7) + 1, xi, yi);
                    powups.add(powup);
                } else if (k >= 0.1 && k <= 0.4) {
                    powup = new FasterFaster(x1, y1, random.nextInt(7) + 1, random.nextInt(7) + 1, xi, yi);
                    powups.add(powup);
                } else if (k >= 0.4 && k <= 0.6) {
                    powup = new SpeedUp(x1, y1, random.nextInt(7) + 1, random.nextInt(7) + 1, xi, yi);
                    powups.add(powup);
                } else if (k >= 0.6 && k <= 0.7) {
                    powup = new Drift(x1, y1, random.nextInt(7) + 1, random.nextInt(7) + 1, xi, yi);
                    powups.add(powup);
                } else if (k >= 0.7 && k <= 0.8) {
                    powup = new MirrorWise(x1, y1, random.nextInt(7) + 1, random.nextInt(7) + 1, xi, yi);
                    powups.add(powup);
                } else if (k >= 0.80 && k <= 1) {
                    double numOfParticles = random.nextInt(RedPowderParticles);
                    for (int i = 0; i < numOfParticles; i++) {
                        boolean x2 = random.nextBoolean();
                        boolean y2 = random.nextBoolean();
                        int xi1;
                        int yi1;
                        if (x2) {
                            xi1 = 1;
                        } else {
                            xi1 = -1;
                        }
                        if (y2) {
                            yi1 = 1;
                        } else {
                            yi1 = -1;
                        }
                        powup = new RedPowder(x1 + xi1 * random.nextInt(20), y1 + yi1 * random.nextInt(20), random.nextInt(10) + 1, random.nextInt(10) + 1, xi1 * xi, yi1 * yi);
                        powups.add(powup);
                    }
                }
            }
        }
    }

    public void powupMove() {
        for (PowerUps powup : powups) {
            if (powup.dropped) {
                powup.y1 = powup.y1 + powup.ydirection * (powup.yspeed);
                powup.x1 = powup.x1 + powup.xdirection * (powup.xspeed);
                powup.setLocation(powup.x1, powup.y1);
                repaint();
            }
        }
    }

    class StunPaddle extends Thread {

        private int duration;

        StunPaddle(int duration) {
            this.duration = duration;
        }

        public void run() {
            Stunned = true;
            try {
                sleep(duration);
                Stunned = false;
            } catch (InterruptedException e) {
                Stunned = false;
            }
        }
    }

    class Poisoned extends Thread {

        private int numOfPoisonIteration;

        public Poisoned(int numOfPoisonIteration) {
            this.numOfPoisonIteration = numOfPoisonIteration;
            poisoned = true;
        }

        public void run() {
            for (int i = 0; i < numOfPoisonIteration; i++) {
                if (!poisoned) {
                    break;
                }
                Double k = random.nextDouble();
                if (k >= 0 && k <= 0.4) {
                    shrinkPaddle(1);//
                    System.out.println("Paddle Shrinked");
                } else if (k >= 0.4 && k <= 0.8) {
                    paddleControl -= 0.01;//
                    System.out.println("Control--");
                } else if (k >= 0.8 && k <= 1) {
                    StunPaddle stun = new StunPaddle(300);
                    System.out.println("Stunned");//
                    stun.run();
                }
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                }
            }
        }
    }

    public void nextLevel(int level) {
        if (level == 0) {
            addBrick(1, 0, 10, 20);
        }
        if (level == 1) {
            for (int i = 0; i < 14; i++) {
                addBrick(20, 0, 10 + i * 30, 20);
            }
        }
        if (level == 2) {
            int j = 0;
            while (j < 14) {
                addBrick(20, 0, 10 + j * 30, 20);
                j = j + 2;
            }
        }
        if (level == 3) {
            for (int i = 0; i < 14; i++) {
                addBrick2(20, 0, 10 + i * 30, 20);
            }
        }
        if (level == 4) {
            for (int i = 0; i < 14; i = i + 2) {
                addBrick2(20, 0, 10 + i * 30, 20);
            }
            for (int i = 1; i < 13; i = i + 2) {
                addBrick3(20, 0, 10 + i * 30, 20);
            }
        }

        if (level == 5) {
            for (int i = 0; i < 14; i++) {
                addBrick4(20, 0, 10 + i * 30, 20, 19 - i);
            }
        }
        if (level == 6) {
            for (int i = 0; i < 14; i++) {
                addBrick4(20, 0, 10 + i * 30, 20, 6 + i);
            }
        }
        if (level == 7) {
            for (int i = 0; i < 14; i++) {
                addBrick5(20, 0, 10 + i * 30, 20);

            }
        }
        if (level == 8) {
            for (int i = 0; i < 14;) {
                Random r = new Random();
                int a = r.nextInt(4);
                i = i + a;
                addBrick5(20, 0, 10 + i * 30, 20);

            }
        }
        if (level == 9) {
			
			addBrick6(0, 10, 20);
			for (int i =0; i<5; i = i+2){
				addBrick7(0, 10+i*30, 20);
				
			}
			for (int i =6; i<11; i = i+2){
				if (i==8){
					
					addBrick9(0, 10+i*30, 20);
					i = i+2;
				}
				addBrick8(0, 10+i*30, 20);
				
			}
		
	}
        if (level == 10) {
            for (int i = 0; i < 14; i = i +2) {
                addBrick10(20, 0, 10 + i * 30, 20);
            }
        }
    }

    public void pause() {
        ball.isStatic = true;
        Stunned = true;
        this.add(label);
    }

    public void resume() {
        ball.isStatic = false;
        Stunned = false;
        this.remove(label);
    }

    class FieldListener implements KeyListener {

        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                pause();
            } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                resume();
            } /*else if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
                ball.defaultColor = false;
                ball.changeColor();
            }
            */
            
        }

        public void keyTyped(KeyEvent e) {

        }

        public void keyReleased(KeyEvent e) {

        }

    }

    public static void infoBox(String infoMessage, String titleBar) {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
}
