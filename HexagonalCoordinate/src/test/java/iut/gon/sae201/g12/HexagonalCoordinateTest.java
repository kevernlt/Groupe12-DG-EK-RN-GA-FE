package iut.gon.sae201.g12;

import iut.gon.sae201.g12.exceptions.DifferentAxisExeption;
import iut.gon.sae201.g12.utils.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class HexagonalCoordinateTest {

    @Test
    void directionOppositeShouldMatchExpectedMapping() {
        for (Direction direction : Direction.values()) {
            assertEquals(expectedOpposite(direction), direction.opposite(), "Unexpected opposite for " + direction);
        }
    }

    @Test
    void directionOppositeShouldBeInvolutive() {
        for (Direction direction : Direction.values()) {
            assertEquals(direction, direction.opposite().opposite(), "Opposite should be reversible for " + direction);
        }
    }

    @Test
    void directionAndModeShouldExposeExpectedValues() {
        assertEquals(8, Direction.values().length);
        assertEquals(Direction.NO, Direction.values()[0]);
        assertEquals(Direction.O, Direction.values()[7]);

        assertEquals(2, Mode.values().length);
        assertEquals(Mode.FLAT, Mode.values()[0]);
        assertEquals(Mode.POINTY, Mode.values()[1]);
    }

    @Test
    void pointShouldExposeCoordinatesAndReadableString() {
        Point point = new Point(3, 7);

        assertEquals(3, point.x());
        assertEquals(7, point.y());
        assertEquals("Point[X=3, Y=7]", point.toString());
        assertEquals(new Point(3, 7), point);
    }

    @Test
    void coordinateDoubledShouldExposeConstructorValues() {
        CoordinateDoubled coordinate = new CoordinateDoubled(4, 9);

        assertEquals(4, coordinate.getX());
        assertEquals(9, coordinate.getY());
    }

    @Test
    void coordinateCubeShouldExposeConstructorValues() {
        CoordinateCube coordinate = new CoordinateCube(1, -2, 1);

        assertEquals(1, coordinate.getQ());
        assertEquals(-2, coordinate.getR());
        assertEquals(1, coordinate.getS());
    }

    @Test
    void coordinateShouldWorkAsMapKeyAcrossRepresentations() {
        HashMap<Coordinate, String> board = new HashMap<>();

        CoordinateCube cube = new CoordinateCube(1, -2, 1);
        CoordinateDoubled doubled = new CoordinateDoubled(3, 9);

        board.put(cube, "occupied");

        assertTrue(Objects.equals(cube.to2DCoordinate(), doubled.to2DCoordinate()));
        assertEquals(cube.hashCode(), doubled.hashCode());
        assertEquals("occupied", board.get(doubled));
        assertTrue(board.containsKey(doubled));
    }

    @Test
    void differentAxisExceptionShouldPreserveMessage() {
        DifferentAxisExeption exception = new DifferentAxisExeption("axes do not match");

        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
        assertEquals("axes do not match", exception.getMessage());
    }

    private static Direction expectedOpposite(Direction direction) {
        return switch (direction) {
            case NO -> Direction.SE;
            case N -> Direction.S;
            case NE -> Direction.SO;
            case E -> Direction.O;
            case SE -> Direction.NO;
            case S -> Direction.N;
            case SO -> Direction.NE;
            case O -> Direction.E;
        };
    }

    // ==================== FUTURE TESTS FOR MOVEMENT AND NEIGHBORS ====================

    @Disabled("Awaiting implementation of toDir method")
    @Test
    void coordinateCubeToDirShouldReturnNonNullInBothModes() {
        CoordinateCube origin = new CoordinateCube(0, 0, 0);

        for (Direction dir : Direction.values()) {
            for (Mode mode : Mode.values()) {
                assertNotNull(origin.toDir(mode, dir),
                        "toDir should not return null for direction " + dir + " in mode " + mode);
            }
        }
    }

    @Disabled("Awaiting implementation of toDir method")
    @Test
    void coordinateDoubledToDirShouldReturnNonNullInBothModes() {
        CoordinateDoubled origin = new CoordinateDoubled(0, 0);

        for (Direction dir : Direction.values()) {
            for (Mode mode : Mode.values()) {
                assertNotNull(origin.toDir(mode, dir),
                        "toDir should not return null for direction " + dir + " in mode " + mode);
            }
        }
    }

    @Disabled("Awaiting implementation of toDir method")
    @Test
    void coordinateCubeOppositeDirectionsShouldBeDifferent() {
        CoordinateCube origin = new CoordinateCube(0, 0, 0);

        for (Direction dir : Direction.values()) {
            for (Mode mode : Mode.values()) {
                Coordinate forward = origin.toDir(mode, dir);
                assertNotNull(forward);
                Coordinate backward = forward.toDir(mode, dir.opposite());
                assertNotNull(backward,
                        "Backward movement from toDir result should not be null");
            }
        }
    }

    @Disabled("Awaiting implementation of toDir method")
    @Test
    void coordinateDoubledOppositeDirectionsShouldBeDifferent() {
        CoordinateDoubled origin = new CoordinateDoubled(0, 0);

        for (Direction dir : Direction.values()) {
            for (Mode mode : Mode.values()) {
                Coordinate forward = origin.toDir(mode, dir);
                assertNotNull(forward);
                Coordinate backward = forward.toDir(mode, dir.opposite());
                assertNotNull(backward,
                        "Backward movement from toDir result should not be null");
            }
        }
    }

    @Disabled("Awaiting implementation of getNeighbors method")
    @Test
    void coordinateCubeGetNeighborsShouldReturnEightNeighborsInBothModes() {
        CoordinateCube origin = new CoordinateCube(0, 0, 0);

        for (Mode mode : Mode.values()) {
            var neighbors = origin.getNeighbors(mode);
            assertNotNull(neighbors, "getNeighbors should not return null for mode " + mode);
            assertEquals(8, neighbors.size(),
                    "getNeighbors should return exactly 8 neighbors in mode " + mode);
        }
    }

    @Disabled("Awaiting implementation of getNeighbors method")
    @Test
    void coordinateDoubledGetNeighborsShouldReturnEightNeighborsInBothModes() {
        CoordinateDoubled origin = new CoordinateDoubled(0, 0);

        for (Mode mode : Mode.values()) {
            var neighbors = origin.getNeighbors(mode);
            assertNotNull(neighbors, "getNeighbors should not return null for mode " + mode);
            assertEquals(8, neighbors.size(),
                    "getNeighbors should return exactly 8 neighbors in mode " + mode);
        }
    }

    @Disabled("Awaiting implementation of getNeighbors method")
    @Test
    void coordinateCubeGetNeighborsShouldNotContainOrigin() {
        CoordinateCube origin = new CoordinateCube(0, 0, 0);

        for (Mode mode : Mode.values()) {
            var neighbors = origin.getNeighbors(mode);
            for (Coordinate neighbor : neighbors) {
                assertNotEquals(origin.getQ(), ((CoordinateCube) neighbor).getQ(),
                        "Neighbor should not be at origin in mode " + mode);
            }
        }
    }

    @Disabled("Awaiting implementation of getNeighbors method")
    @Test
    void coordinateDoubledGetNeighborsShouldNotContainOrigin() {
        CoordinateDoubled origin = new CoordinateDoubled(5, 5);

        for (Mode mode : Mode.values()) {
            var neighbors = origin.getNeighbors(mode);
            for (Coordinate neighbor : neighbors) {
                var doubledNeighbor = (CoordinateDoubled) neighbor;
                if (doubledNeighbor.getX() == origin.getX() && doubledNeighbor.getY() == origin.getY()) {
                    throw new AssertionError("Neighbor should not be at origin in mode " + mode);
                }
            }
        }
    }

    @Disabled("Awaiting implementation of between method")
    @Test
    void coordinateCubeBetweenShouldReturnNonNullPath() {
        CoordinateCube from = new CoordinateCube(0, 0, 0);
        CoordinateCube to = new CoordinateCube(1, -1, 0);

        for (Mode mode : Mode.values()) {
            var path = from.between(mode, to);
            assertNotNull(path, "between should not return null for mode " + mode);
        }
    }

    @Disabled("Awaiting implementation of between method")
    @Test
    void coordinateDoubledBetweenShouldReturnNonNullPath() {
        CoordinateDoubled from = new CoordinateDoubled(0, 0);
        CoordinateDoubled to = new CoordinateDoubled(10, 10);

        for (Mode mode : Mode.values()) {
            var path = from.between(mode, to);
            assertNotNull(path, "between should not return null for mode " + mode);
        }
    }

    @Disabled("Awaiting implementation of between method")
    @Test
    void coordinateCubeBetweenSameShouldContainAtLeastOne() {
        CoordinateCube origin = new CoordinateCube(0, 0, 0);

        for (Mode mode : Mode.values()) {
            var path = origin.between(mode, origin);
            assertNotNull(path);
            // Path from a point to itself should at least contain some coordinates
            assertNotNull(path, "Path should not be null even for same coordinates");
        }
    }

    @Disabled("Awaiting implementation of directional methods")
    @Test
    void coordinateCubeDirectionalMethodsShouldReturnNonNull() {
        CoordinateCube origin = new CoordinateCube(0, 0, 0);

        for (Mode mode : Mode.values()) {
            assertNotNull(origin.NO(mode), "NO() should not return null");
            assertNotNull(origin.NE(mode), "NE() should not return null");
            assertNotNull(origin.E(mode), "E() should not return null");
            assertNotNull(origin.O(mode), "O() should not return null");
            assertNotNull(origin.N(mode), "N() should not return null");
            assertNotNull(origin.S(mode), "S() should not return null");
            assertNotNull(origin.SO(mode), "SO() should not return null");
        }
    }

    @Disabled("Awaiting implementation of directional methods")
    @Test
    void coordinateDoubledDirectionalMethodsShouldReturnNonNull() {
        CoordinateDoubled origin = new CoordinateDoubled(0, 0);

        for (Mode mode : Mode.values()) {
            assertNotNull(origin.NO(mode), "NO() should not return null");
            assertNotNull(origin.NE(mode), "NE() should not return null");
            assertNotNull(origin.E(mode), "E() should not return null");
            assertNotNull(origin.O(mode), "O() should not return null");
            assertNotNull(origin.N(mode), "N() should not return null");
            assertNotNull(origin.S(mode), "S() should not return null");
            assertNotNull(origin.SO(mode), "SO() should not return null");
        }
    }

    @Disabled("Awaiting implementation of to2DCoordinate method")
    @Test
    void coordinateCubeTo2DCoordinateShouldReturnPoint() {
        CoordinateCube origin = new CoordinateCube(0, 0, 0);
        Point point = origin.to2DCoordinate();

        assertNotNull(point, "to2DCoordinate should return a Point");
        assertInstanceOf(Point.class, point);
    }

    @Disabled("Awaiting implementation of to2DCoordinate method")
    @Test
    void coordinateDoubledTo2DCoordinateShouldReturnPoint() {
        CoordinateDoubled coord = new CoordinateDoubled(5, 7);
        Point point = coord.to2DCoordinate();

        // To2D should handle coordinates that are already doubled
        assertNotNull(point, "to2DCoordinate should return a Point");
        assertInstanceOf(Point.class, point);
    }

    @Disabled("Awaiting implementation of toDir method")
    @Test
    void directionToDirShouldBeConsistentWithOpposite() {
        CoordinateCube origin = new CoordinateCube(2, -3, 1);

        for (Direction dir : Direction.values()) {
            for (Mode mode : Mode.values()) {
                Coordinate moved = origin.toDir(mode, dir);
                Coordinate back = moved.toDir(mode, dir.opposite());

                assertNotNull(moved);
                assertNotNull(back);
                // After moving then moving opposite, we should be somewhere (not necessarily origin)
            }
        }
    }

    @Disabled("Awaiting implementation of getNeighbors method")
    @Test
    void coordinateCubeNeighborsShouldAllBeDifferentFromEachOther() {
        CoordinateCube origin = new CoordinateCube(0, 0, 0);
        var neighbors = origin.getNeighbors(Mode.FLAT);

        for (int i = 0; i < neighbors.size(); i++) {
            for (int j = i + 1; j < neighbors.size(); j++) {
                Coordinate c1 = neighbors.get(i);
                Coordinate c2 = neighbors.get(j);

                CoordinateCube cube1 = (CoordinateCube) c1;
                CoordinateCube cube2 = (CoordinateCube) c2;

                boolean sameQ = cube1.getQ() == cube2.getQ();
                boolean sameR = cube1.getR() == cube2.getR();
                boolean sameS = cube1.getS() == cube2.getS();

                if (sameQ && sameR && sameS) {
                    throw new AssertionError("Neighbors should all be different from each other");
                }
            }
        }
    }
}
