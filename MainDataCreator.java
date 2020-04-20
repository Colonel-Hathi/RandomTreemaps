	import java.awt.Dimension;
import java.awt.List;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import experimentextensions.RandomTreeDraw;
import experimentextensions.RandomTreeMapNode;
import experimentextensions.RandomTreeNodeSize;
import net.bouthier.treemapSwing.TMAlgorithmClassicDot;
import net.bouthier.treemapSwing.TMComputeDraw;
import net.bouthier.treemapSwing.TMComputeSize;
import net.bouthier.treemapSwing.TMView;
import net.bouthier.treemapSwing.TreeMap;

public class MainDataCreator {
	final ArrayList<JFrame> framelist = new ArrayList<JFrame>();
	String labels = "";

	public MainDataCreator(String fileName, int amountTreeMaps, int startindex)
			throws InvocationTargetException, InterruptedException {
		System.out.println("MainDataCreator.MainDataCreator()");
		createOutputFolders();
		
		createTreemaps(fileName, amountTreeMaps, startindex);
		
		// SwingUtilities.invokeLater(new Runnable() {
		//
		// @Override
		// public void run() {
		// int index = startindex * amountTreeMaps + currentIndex;
		// TMNode root = new RandomTreeMapNode(new Random(), 1000f, 1000f, 4, 0);
		// System.out.println("Done Create Tree: " + index);
		// TreeMap treemap = new TreeMap(root);
		//
		// TMView view = buildNewView(treemap);
		// view.setPreferredSize(new Dimension(300, 300));
		// view.setFileID("png", "data/large/treeMap" + index + ".png");
		// view.setAlgorithm(TMView.SQUARIFIED);
		//
		// JFrame frame = new JFrame();
		//// frame.setSize(300, 300);
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.setContentPane(view);
		// frame.pack();
		// frame.setVisible(true);
		// framelist.add(frame);
		//
		// view = buildNewView(treemap);
		// view.setPreferredSize(new Dimension(96, 96));
		// view.setFileID("png", "data/small/treeMap" + index + ".png");
		// view.setAlgorithm(TMView.SQUARIFIED);
		//
		// frame = new JFrame();
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.setContentPane(view);
		// frame.pack();
		// frame.setVisible(true);
		// framelist.add(frame);
		//
		// /*
		// * included already in TMView paintcompontent
		// */
		//// BufferedImage createImage = view.createImage();
		//// try {
		//// ImageIO.write(createImage, "png", new File("data/treeMap" + index +
		// ".png"));
		//// } catch (IOException e) {
		//// e.printStackTrace();
		//// }
		//
		// index++;
		// }
		// });
		// }
	}

	private void createTreemaps(String fileName, int amountTreeMaps, int startIndex) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame frame = new JFrame();
				JPanel panelContent = new JPanel();
				// frame.setSize(300, 300);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				JScrollPane pane = new JScrollPane(panelContent);

				frame.setContentPane(pane);
				frame.setVisible(true);

				for (int j = 0; j < amountTreeMaps; j++) {
					// final int currentIndex = j;
					final int stepIndex = j + 0;

					// int index = startIndex * amountTreeMaps + currentIndex + startIndex;
					int index2 = startIndex * amountTreeMaps + stepIndex + startIndex;
					RandomTreeMapNode root = new RandomTreeMapNode(new Random(), 1000f, 1000f, 4, 0);
					int count = root.countLeafs(0);
					while (count < 3 || count > 15) {
						root = new RandomTreeMapNode(new Random(), 1000f, 1000f, 4, 0);
						count = root.countLeafs(0);
					}
					
					int searchObjIndex = (int) Math.floor(count * Math.random());
					RandomTreeMapNode searchObj = ((RandomTreeMapNode)root).getChild(searchObjIndex);
					searchObj.setSearchObject(true);
					while (searchObj.getSize() < 10) {
						searchObj.setSearchObject(false);
						searchObjIndex = (int) Math.floor(count * Math.random());
						searchObj = ((RandomTreeMapNode)root).getChild(searchObjIndex);
						searchObj.setSearchObject(true);
					}
					// Print treemap					
					System.out.println("Done Create Tree: " + index2 + "\t" + count);
					TreeMap treemap = new TreeMap(root);
					TMView view = buildNewView(treemap);
					view.setPreferredSize(new Dimension(300, 300));
					view.setFileID("png", "datatest/large/" + String.valueOf(searchObj.getSize()) + ".png");
					
					String algorithmID = "ClassicWithDot";
					view.addAlgorithm(new TMAlgorithmClassicDot(), algorithmID);
					view.setAlgorithm(algorithmID);
					
					labels += String.valueOf(searchObj.getSize()) + "\n";
					
//					view.setAlgorithm(TMView.SQUARIFIED);
					panelContent.add(view);

					view = buildNewView(treemap);
					view.setPreferredSize(new Dimension(96, 96));
					view.setFileID("png", "datatest/small/" + String.valueOf(searchObj.getSize()) + ".png");
//					view.setAlgorithm(TMView.SQUARIFIED);
					view.addAlgorithm(new TMAlgorithmClassicDot(), algorithmID);
					view.setAlgorithm(algorithmID);
					

					panelContent.add(view);
				}
				createlabelCSV();
				frame.pack();
				frame.setVisible(true);
			}
		});		
	}
	
	public void createlabelCSV() {
		try (PrintWriter printer = new PrintWriter(new File("datatest/labelstest2.csv"))) {
			printer.print(labels);
		} catch (FileNotFoundException e) {
		  System.out.println(e.getMessage());
		}
	}

	private void createOutputFolders() {
		String foldername = "datatest";
		String folderSmall = "small";
		String folderBig = "large";
		
		File file = new File(foldername);
        if (!file.exists()) {
            if (file.mkdir()) {
                System.out.println(foldername + " Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        } else {
        	System.out.println("Primary folder exists already");
        }

        File files = new File(foldername + File.separator + folderSmall);
        if (!files.exists()) {
            if (files.mkdirs()) {
                System.out.println(folderSmall + "directory created!");
            } else {
                System.out.println("Failed to create " + folderSmall + "!");
            }
        }
        
        files = new File(foldername + File.separator + folderBig);
        if (!files.exists()) {
            if (files.mkdirs()) {
                System.out.println(folderBig + "directory created!");
            } else {
                System.out.println("Failed to create " + folderSmall + "!");
            }
        }
	}

	private void dispose() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				for (JFrame jFrame : framelist) {
					System.out.println("MainDataCreator.MainDataCreator(...).new Runnable() {...}.run dispose()");
					jFrame.setVisible(false);
					jFrame.dispatchEvent(new WindowEvent(jFrame, WindowEvent.WINDOW_CLOSING));
					jFrame.dispose();
				}
			}
		});
	}

	/**
	 * Build a new TMView, shows it in a frame, and return the TMView.
	 * 
	 * @param treemap
	 */
	private TMView buildNewView(TreeMap treemap) {
		TMComputeSize fSize = new RandomTreeNodeSize();
		TMComputeDraw fDraw = new RandomTreeDraw();
		return treemap.getView(fSize, fDraw);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IntStream.range(0, 1).forEach(index -> {
			try {
				System.out.println("MainDataCreator.main()" + index);
				MainDataCreator mainDataCreator = new MainDataCreator("data.csv", 2000, index);
				// mainDataCreator.dispose();
			} catch (InvocationTargetException | InterruptedException e) {
				e.printStackTrace();
			}
		});
	}

}
