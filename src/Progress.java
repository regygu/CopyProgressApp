import javax.swing.*;
import java.io.*;

public class Progress extends SwingWorker {

    File from;
    File to;
    FileInputStream in;
    FileOutputStream out;
    JProgressBar jProgressBar;

    public Progress(File from, File to, JProgressBar jProgressBar) {
        this.from = from;
        this.to = to;
        this.jProgressBar = jProgressBar;
    }

    public void copy() {

        try {
            in = new FileInputStream(from);
            out = new FileOutputStream(to);
            long fileLenght = from.length();
            long counter = 0;
            int read = 0;
            byte[] buffer = new byte[1024];

            while ((read = in.read(buffer)) != -1 && !isCancelled()) {
                counter += read;
                System.out.println(fileLenght);
                System.out.println(counter);
                jProgressBar.setValue((int) (Math.floor((1.0 * counter / fileLenght) * 100)));
                System.out.println((int) (Math.floor((1.0 * counter / fileLenght) * 100)));
                out.write(buffer, 0, read);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                in.close();
                out.close();
                if (isCancelled()) {
                    to.delete();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected Integer doInBackground() throws Exception {
        copy();
        return null;
    }

    @Override
    protected void done() {
        jProgressBar.setVisible(false);
    }
}
