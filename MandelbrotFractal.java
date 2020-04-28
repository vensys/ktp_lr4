import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.awt.geom.Rectangle2D;

public class MandelbrotFractal extends FractalGenerator {

    public static final int MAX_ITERATIONS = 2000;

    public void getInitialRange(Rectangle2D.Double range) {
        range.setRect(-2, -1.5, 3, 3);
    }

    public int numIterations(double x, double y) {
        ComplexNumber z = new ComplexNumber(0d, 0d);
        ComplexNumber c = new ComplexNumber(x, y);
        for (int i = 1; i <= MAX_ITERATIONS; i++) {
            if (z.getSquaredModulus() > 4) {
                return i;
            }
            z = z.getSquare().add(c);
        }
        return -1;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    static class ComplexNumber {

        private double realPart;
        private double imaginPart;

        public double getSquaredModulus() {
            return realPart*realPart + imaginPart*imaginPart;
        }

        public ComplexNumber getSquare() {
            double newRealPart = realPart*realPart - imaginPart*imaginPart;
            double newImaginPart = 2*realPart*imaginPart;
            return new ComplexNumber(newRealPart, newImaginPart);
        }

        public ComplexNumber add(ComplexNumber complexNumber) {
            this.realPart += complexNumber.getRealPart();
            this.imaginPart += complexNumber.getImaginPart();
            return this;
        }
    }
}
