package utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PolygonalNumberTest {

    @Test
    void polygonalNumber() {
        for (int i = 1; i < 10; i++) {
            assertEquals(1, PolygonalNumber.polygonalNumber(i, 1));
            assertEquals(i, PolygonalNumber.polygonalNumber(i, 2));
        }
        assertEquals(10, PolygonalNumber.polygonalNumber(3, 4));
        assertEquals(370, PolygonalNumber.polygonalNumber(10, 10));
        assertEquals(500051, PolygonalNumber.polygonalNumber(101, 101));
    }
    @Test
    void isPolygonalNumber() {
        assertArrayEquals(new int[] {8}, PolygonalNumber.isPolygonalNumber(40));
        assertArrayEquals(new int[] {}, PolygonalNumber.isPolygonalNumber(101));
        assertArrayEquals(new int[] {8, 15, 48}, PolygonalNumber.isPolygonalNumber(280));
        assertArrayEquals(new int[] {19, 66, 601}, PolygonalNumber.isPolygonalNumber(1_800));
        assertArrayEquals(new int[] {33_334}, PolygonalNumber.isPolygonalNumber(99_999));
        //assertArrayEquals(new int[] {}, PolygonalNumber.isPolygonalNumber(23333333333L));
        assertTrue(PolygonalNumber.isPolygonalNumber(3, 3));
    }
}