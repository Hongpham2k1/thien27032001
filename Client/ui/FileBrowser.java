package Client.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

import Client.FTPClient;
import Client.UploadThread;

public class FileBrowser implements ActionListener	{
	
	private FTPClient ftpClient=null;
	private JLabel messageText=null;
	private DefaultListModel<String> listValues =null;
	private JProgressBar jProgressBar=null;
	private JFrame jframe=null;
	
	
	public FileBrowser(JLabel filePathLabel, DefaultListModel<String> listValues, 
			JProgressBar jProgressBar,JFrame jframe) {
		this.messageText = filePathLabel;
		this.listValues=listValues;
		this.jProgressBar = jProgressBar;
		this.jframe=jframe;
	}
	
	@SuppressWarnings("static-access")
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setMultiSelectionEnabled(true);
		int returnCode = fileChooser.showOpenDialog(null);
		
		if (returnCode == JFileChooser.APPROVE_OPTION){
			File files[] = fileChooser.getSelectedFiles();
			for(File file :files){
				UploadThread uploadThread=new UploadThread(jframe,file,listValues,messageText);
				FTPClient.threadsMap.put(uploadThread,null);
				uploadThread.start();
				try {
					Thread.currentThread().sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}
	}


	
	

}
