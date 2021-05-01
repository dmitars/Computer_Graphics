package letter;

import javafx.scene.shape.TriangleMesh;

import java.util.HashMap;
import java.util.TreeSet;

public class LetterMesh {

    private static final HashMap<Integer, TreeSet<Integer>> neighbours = new HashMap<>(){{
        put(0,new TreeSet<>(){{
            add(1);
            add(3);
            add(21);
        }});
        put(1,new TreeSet<>(){{
            add(2);
            add(15);
        }});
        put(2,new TreeSet<>(){{
            add(3);
            add(16);
        }});
        put(3,new TreeSet<>(){{
            add(22);
        }});
        put(10,new TreeSet<>(){{
            add(11);
            add(13);
            add(19);
        }});
        put(11,new TreeSet<>(){{
            add(12);
            add(18);
        }});
        put(12, new TreeSet<>(){{
            add(16);
            add(13);
        }});
        put(13,new TreeSet<>(){{
            add(15);
        }});
        put(15,new TreeSet<>(){{
            add(16);
        }});
        put(16,new TreeSet<>());
        put(17,new TreeSet<>(){{
            add(18);
            add(20);
            add(22);
        }});
        put(18,new TreeSet<>(){{
            add(19);
        }});
        put(19,new TreeSet<>(){{
            add(20);
        }});
        put(20,new TreeSet<>(){{
            add(21);
        }});
        put(21,new TreeSet<>(){{
            add(22);
        }});
        put(22,new TreeSet<>());
    }};


    private static final int[] FACES = new int[]{


            1, 0, 0, 0, 3, 0,
            1, 0, 3, 0, 2, 0,
            7, 0, 6, 0, 9, 0,
            7, 0, 9, 0, 8, 0,
            8, 0, 9, 0, 0, 0,
            8, 0, 0, 0, 1, 0,
            9, 0, 6, 0, 3, 0,
            9, 0, 3, 0, 0, 0,
            7, 0, 8, 0, 1, 0,
            7, 0, 1, 0, 2, 0,
            6, 0, 7, 0, 2, 0,
            6, 0, 2, 0, 3, 0,

            11, 0, 7, 0, 8, 0,
            11, 0, 8, 0, 10, 0,
            10, 0, 8, 0, 15, 0,
            10, 0, 15, 0, 13, 0,
            11, 0, 10, 0, 13, 0,
            11, 0, 13, 0, 12, 0,
            7, 0, 11, 0, 12, 0,
            7, 0, 12, 0, 16, 0,
            13, 0, 15, 0, 16, 0,
            13, 0, 16, 0, 12, 0,


            9, 0, 6, 0, 18, 0,
            19, 0, 9, 0, 18, 0,
            21, 0, 9, 0, 19, 0,
            20, 0, 21, 0, 19, 0,
            20, 0, 19, 0, 18, 0,
            17, 0, 20, 0, 18, 0,
            17, 0, 18, 0, 6, 0,
            22, 0, 17, 0, 6, 0,
            22, 0, 21, 0, 20, 0,
            17, 0, 22, 0, 20, 0,

    };


    public static HashMap<Integer, TreeSet<Integer>> getNeighbours() {
        return neighbours;
    }

    private final TriangleMesh mesh;

    public LetterMesh(float width, float height) {
        this.mesh = new TriangleMesh();
        this.mesh.getPoints().addAll(LetterMesh.calculatePoints(width, height));
        this.mesh.getTexCoords().addAll(0, 0);
        this.mesh.getFaces().addAll(LetterMesh.FACES);
    }

    public TriangleMesh getMesh() {
        return this.mesh;
    }

    private static float[] calculatePoints(float width, float height) {
        return new float[]{
                0, 1.7f * height, 50, //0
                50, 1.7f * height, 50, //1
                50, 1.7f * height, 0, //2
                0, 1.7f * height, 0, //3
                0, 1.7f * height, 50, //4

                0, 0, 50, //5
                0, 0, 0, //6
                50, 0, 0, //7
                50, 0, 50, //8
                0, 0, 50, //9

                110, 0, 50, //10
                110, 0, 0, //11
                110, 50, 0, //12
                110, 50, 50, //13
                110, 0, 50, //14

                50, 50, 50, //15
                50, 50, 0, //16


                -60, 50, 0, //17
                -60, 0, 0, //18
                -60, 0, 50, //19
                -60, 50, 50, //20

                0, 50, 50, //21
                0, 50, 0, //22
        };
    }

}
