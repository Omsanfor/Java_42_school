import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class MiniShell {
    Path curDir;

    public void ls() throws IOException {
        List<Path> listPaths;
        listPaths = Files.walk(curDir, 1).collect(Collectors.toList());
        for (Path x : listPaths)
            if (!x.equals(curDir))
                System.out.println(x.getFileName() + " " + Files.size(x) + " KB");
    }

    public void cd(Path newPath) throws IOException {
        Path sumOfPaths = Paths.get(getCurDir().toString() + "/" + newPath.toString());
        if (Files.exists(sumOfPaths) && Files.isDirectory(sumOfPaths)) {
            setCurDir(sumOfPaths.normalize());
            System.out.println(getCurDir());
        }
        else
            System.out.println("Wrong path");
    }

    public void mv(Path srcP, Path destP) throws IOException {
        Path tmpS = Paths.get(getCurDir() + "/" + srcP).normalize();
        Path tmpD = Paths.get(getCurDir() + "/" + destP).normalize();

        if (Files.isRegularFile(tmpS)) {
            if (Files.isDirectory(tmpD))
                tmpD = Paths.get(tmpD + "/" + tmpS.getFileName());
            Files.move(tmpS, tmpD, REPLACE_EXISTING);
        }
        else
            System.out.println("Wrong source file path");

    }

    public MiniShell(Path curDir) {
        System.out.println(curDir);
        if (!Files.exists(curDir)) {
            try {
                Files.createDirectory(curDir);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.curDir = curDir;
    }

    public Path getCurDir() {
        return curDir;
    }

    public void setCurDir(Path curDir) {
        this.curDir = curDir;
    }
}
