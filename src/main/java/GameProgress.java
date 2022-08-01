import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class GameProgress implements Serializable {
    private static final long serialVersionUID = 1L;

    private final int health;
    private final int weapons;
    private final int lvl;
    private final double distance;

    public GameProgress(int health, int weapons, int lvl, double distance) {
        this.health = health;
        this.weapons = weapons;
        this.lvl = lvl;
        this.distance = distance;
    }

    public static void main(String[] args) throws IOException {
        GameProgress gameProgress = new GameProgress(90, 90, 90, 90.1);
        GameProgress gameProgress1 = new GameProgress(91, 91, 91, 91.1);
        GameProgress gameProgress2 = new GameProgress(92, 92, 92, 92.2);
        saveGame("C://Games1/savegames/save.dat", gameProgress);
        saveGame("C://Games1/savegames/save2.dat", gameProgress1);
        saveGame("C://Games1/savegames/save3.dat", gameProgress2);
        List<String> list = new ArrayList<>();
        list.add("save.dat");
        list.add("save2.dat");
        list.add("save3.dat"); // во время вебинара преподаватель порекомендовал прописывать не абсолютный путь файла, а имя файла
//        zipFiles("C://Games1/savegames/zip.zip", list);
//        deleteFiles("C://Games1/savegames", list);
        zipFiles1("C://Games1/savegames/zip.zip", "C://Games1/savegames"); // мой метод

    }

    // мой метод
    private static void zipFiles1(String pathAndNameOfZip, String pathOfDirectory) {
        File file = new File(pathOfDirectory);
        List<String> list = new ArrayList<>();
        if (file.isDirectory()) {
            for (File item : file.listFiles()) {
                if (item.isDirectory()) {
                    list.add(item.getName());
                } else {
                    list.add(item.getName());
                }
            }
        }
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(pathAndNameOfZip));
             FileInputStream fis = new FileInputStream(pathAndNameOfZip)) {
            for (String list2 : list) {
                ZipEntry entry = new ZipEntry(list2);
                zout.putNextEntry(entry);
                byte[] buffer = new byte[fis.available()];
                zout.write(buffer);
                for (String string3 : list) {
                    File file1 = new File(pathOfDirectory + "/" + string3);
                    file1.delete();
                }
            }
            zout.closeEntry();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }

    // метод из задания
    private static void zipFiles(String path, List<String> list) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(path));
             FileInputStream fis = new FileInputStream(path)) {
            for (String list2 : list) {
                ZipEntry entry = new ZipEntry(list2);
                zout.putNextEntry(entry);
                byte[] buffer = new byte[fis.available()];
                zout.write(buffer);
            }
            zout.closeEntry();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void deleteFiles(String path, List<String> list) {
        File file = new File(path);
        for (String string3 : list) {
            File file1 = new File(path + "/" + string3);
            file1.delete();
        }

    }

    private static void saveGame(String path, GameProgress gameProgress) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public String toString() {
        return "GameProgress{ " +
                "health = " + health +
                ", weapons = " + weapons +
                ", lvl = " + lvl +
                ", distance = " + distance +
                '}';
    }
}