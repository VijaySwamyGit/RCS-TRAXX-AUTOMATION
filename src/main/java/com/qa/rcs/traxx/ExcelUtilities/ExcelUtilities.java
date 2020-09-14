package com.qa.rcs.traxx.ExcelUtilities;





import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.qa.rcs.traxx.AutomationUtils.TestUtil;


	public class ExcelUtilities {
		
		//REFERENCES---------------------------------------------
		Workbook wb=null;
	
		//UTILITIES---------------------------------------------------------------
		//getLastRowNum ===================================================================
		/**
		 * Created By : VIJAY
		 * Created Date:
		 * Reviewed By:
		 * Reviewed Date:
		 * Parameters:
		 * Return Type:
		 * Description:
		 * Purpose:
		 */
				//getLastRowNum 
				public int getLastRownum(String FileName,String SheetName) throws Exception
				{
					FileInputStream fis = new FileInputStream("E:\\SVN-V3-automation\\Automation\\src\\main\\java\\com\\qa\\vidalhealth\\TestData\\"+FileName+".xlsx");
					Workbook wb = WorkbookFactory.create(fis);
					
					Sheet s1 = wb.getSheet(SheetName);
					int rowcount = s1.getLastRowNum();
					return rowcount;

				
				}
				//***************************************************************************
				
			    //getLastCellNum ==============================================================

				/**
				 * Created By : VIJAY
				 * Created Date:
				 * Reviewed By:
				 * Reviewed Date:
				 * Parameters:
				 * Return Type:
				 * Description:
				 * Purpose:
				 */
					public void getLastCellnum() throws Exception
					{
						FileInputStream fis = new FileInputStream("path");
						Workbook wb = WorkbookFactory.create(fis);
						
						Sheet s1 = wb.getSheet("sheet1");
						int rowcount = s1.getLastRowNum();
								
						Row r1 = s1.getRow(1);
						int columncount = r1.getLastCellNum();

						fis.close();
					
					}
					//*******************************************************************************
					// rowCount Method() START=======================================================

					/**
					 * Created By : VIJAY
					 * Created Date:
					 * Reviewed By:
					 * Reviewed Date:
					 * Parameters:
					 * Return Type:
					 * Description:
					 * Purpose:
					 */
					public int rowCount(String FileName,String SheetName)
					{
						System.out.println("SheetName--"+SheetName);
						System.out.println("FileName----"+FileName);
						FileInputStream fis=null;
						//Workbook wb=null;
						Sheet sh=null;
						int rc=0;
						try
						{
							/*fis=new FileInputStream("E:\\old system's-D drive data\\AUTOMATIOM-06122018\\Maven automation project\\src\\"
									+ "main\\java\\com\\qa\\vidalhealth\\TestData\\"+FileName+".xlsx");*/
							
							fis = new FileInputStream("E:\\SVN-V3-automation\\Automation\\src\\main\\java\\com\\qa\\vidalhealth\\TestData\\"+FileName+".xlsx");
							
							//wb=new HSSFWorkbook(fin);
							wb=WorkbookFactory.create(fis);
							sh=wb.getSheet(SheetName);
							System.out.println("SheetNameee--"+SheetName);
							System.out.println("SheetNameee----"+sh);

							if (sh==null) return -1;
							rc=sh.getPhysicalNumberOfRows();
							
						}catch(Exception e)
						{
							return -1;
						}
						finally
						{
							try
							{
								fis.close();
								//wb.close();
							}catch(Exception e)
							{
								return -1;
							}
						}
						return rc-1;
					}
					//rowCount Method() END*************************************************************************
					
					//importExcelFile START===================================================================
					/**
					 * Created By : VIJAY
					 * Created Date:
					 * Reviewed By:
					 * Reviewed Date:
					 * Parameters:
					 * Return Type:
					 * Description:
					 * Purpose:
					 */
					public boolean importExcelFile(String File)
					{
						FileInputStream fis=null;
						try
						{
							fis=new FileInputStream(File);
							wb=WorkbookFactory.create(fis);
							if (wb != null) return true;
						}catch(Exception e)
						{
							return false;
						}
						return true;
					}
					//importExcelFile ENDS***********************************************************************
					
					//==================================================================================================
					/**
					 * Created By : VIJAY
					 * Created Date:
					 * Reviewed By:
					 * Reviewed Date:
					 * Parameters:
					 * Return Type:
					 * Description:
					 * Purpose:
					 */
					public int rowCount(String SheetName)
					{
						Sheet sh=null;
						int rc=0;
						try
						{
							sh=wb.getSheet(SheetName);
							if (sh==null) return -1;
							rc=sh.getPhysicalNumberOfRows();
							
						}catch(Exception e)
						{
							return -1;
						}
						
						return rc;
					}
					//********************************************************************************************
					
					//========================================================================================
					/**
					 * Created By : VIJAY
					 * Created Date:
					 * Reviewed By:
					 * Reviewed Date:
					 * Parameters:
					 * Return Type:
					 * Description:
					 * Purpose:
					 */
					public String[] getCellData(String FileName,String SheetName,String colName,String NumRow)
					//public List[] getCellData(String FileName,String SheetName,String colName)
					{
						FileInputStream fis=null;
						//Workbook wb=null;
						Sheet sh=null;
						Row row=null;
						Cell cell=null;
						String strCellData=null;
						int colNum=0;
						int NumRow2=Integer.parseInt(NumRow);
						System.out.println("NumRow2==="+NumRow2);
					    String strArr[]=new String[NumRow2];
						
						try
						{
							fis=new FileInputStream("E:\\SVN-V3-automation\\Automation\\src\\main\\java\\com\\qa\\vidalhealth\\TestData\\"+FileName+".xlsx");
							wb=WorkbookFactory.create(fis);
							if (wb ==null)  return null;
							sh=wb.getSheet(SheetName);
							if (sh==null)  return null;
							row=sh.getRow(0);
							for (int c=0;c<row.getPhysicalNumberOfCells();c++)
							{
								cell=row.getCell(c);
								String ColumnValue=cell.getStringCellValue();
								if (ColumnValue.trim().equalsIgnoreCase(colName.trim()))
								{
									colNum=c;
									break;
								}
							}
							//row=sh.getRow(rownum-1);
							System.out.println("colNum--"+colNum);
							cell=row.getCell(colNum);
							//
							
							for(int i = 1;i<=sh.getLastRowNum();i++)
							{
							     row = sh.getRow(i);
								 cell = row.getCell(colNum);
								cell.setCellType(cell.CELL_TYPE_STRING);
								strArr[i-1] = cell.getStringCellValue();
								System.out.println("cell value--"+(i-1)+strArr[i-1]);
							}
	
						}catch(Exception e)
						{
							e.printStackTrace();
						}
						finally
						{
							try
							{
								fis.close();
								//wb.close();
							}catch(Exception e)
							{
								e.printStackTrace();
							}
						}
						//
						return strArr;
					}
					//************************************************************************************************
					/*
				//========================================================================================================================	
					*//**
					 * Created By: VIJAY
					 * Created Date:
					 * Reviewed By:
					 * Reviewed Date:
					 * Parameters:
					 * Return Type:
					 * Description:
					 * Purpose:
					 *//*
					public String getCellData(String SheetName,int rownum,String colName)
					{
						Sheet sh=null;
						Row row=null;
						Cell cell=null;
						String strCellData=null;
						int colNum=0;
						try
						{
							if (wb ==null)  return null;
							sh=wb.getSheet(SheetName);
							if (sh==null)  return null;
							row=sh.getRow(0);
							for (int c=0;c<row.getPhysicalNumberOfCells();c++)
							{
								cell=row.getCell(c);
								String ColumnValue=cell.getStringCellValue();
								if (ColumnValue.trim().equalsIgnoreCase(colName.trim()))
								{
									colNum=c;
									break;
								}
							}
							row=sh.getRow(rownum-1);
							cell=row.getCell(colNum);
							if (cell==null || cell.getCellTypeEnum()==cell.getCellTypeEnum().BLANK)
							{
								strCellData="";
							}
							else if(cell.getCellTypeEnum()==cell.getCellTypeEnum().STRING)
							{
								strCellData=cell.getStringCellValue();
							}
							else if(cell.getCellTypeEnum()==cell.getCellTypeEnum().BOOLEAN)
							{
								strCellData=String.valueOf(cell.getBooleanCellValue());
							}
							else if(cell.getCellType()==cell.getCellTypeEnum().NUMERIC)
							{
								if (HSSFDateUtil.isCellDateFormatted(cell))
								{
									double d=cell.getNumericCellValue();
									Calendar cal=Calendar.getInstance();
									cal.setTime(HSSFDateUtil.getJavaDate(d));
									int month=cal.get(Calendar.MONTH)+1;
									int day=cal.get(Calendar.DAY_OF_MONTH);
									int year=cal.get(Calendar.YEAR);
									String sDate=month+"/"+day+"/"+year;
									strCellData=sDate;
								}
								else
								{
									strCellData=String.valueOf(cell.getNumericCellValue());
								}
							}
							
						}catch(Exception e)
						{
							e.printStackTrace();
						}
						
						return strCellData;
					}*/
					//**************************************************************************************************************
					
					//
					
					//
					
					//WRITE to EXCEL
					public String writeToExcel(String preAuthNo) {
						
						FileInputStream fis=null;
						//Workbook wb=null;
						Sheet sh=null;
						Row row=null;
						Cell cell=null;
						
						try {
							fis = new FileInputStream("E:\\old system's-D drive data\\AUTOMATION01042019\\src\\"
									+ "main\\java\\com\\qa\\vidalhealth\\TestData\\PreauthGEDPageTestData.xlsx");
							
							Workbook wb=null;
							try {
								wb = WorkbookFactory.create(fis);
							} catch (InvalidFormatException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if (wb ==null)  return null;
						sh=wb.createSheet("PreauthGED1");
							if (sh==null)  return null;
							row=sh.createRow(0);
							
							cell=row.createCell(3);
							
							cell.setCellValue(preAuthNo);
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						//Workbook wb = WorkbookFactory.create(fis);
						
						FileOutputStream fos;
						try {
							fos = new FileOutputStream("E:\\old system's-D drive data\\AUTOMATION01042019\\src\\"
									+ "main\\java\\com\\qa\\vidalhealth\\TestData\\PreauthGEDPageTestData.xlsx");
							try {
								wb.write(fos);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						
						return preAuthNo;
					}
					
					
					//WRITE to EXCEL
					
					//write to EXCEL old code
					public void writeToExceloldcode(String str, String Filename, String sheetname) throws Exception
					{
						System.out.println("newDate---nd"+str);
						//TestUtil testUtil = new TestUtil();
						FileInputStream fis = new FileInputStream("E:\\SVN-V3-automation\\Automation\\src\\main\\java\\com\\qa\\vidalhealth\\TestData\\"+Filename+".xlsx");
						Workbook wb = WorkbookFactory.create(fis);
						Sheet s1 = wb.getSheet(sheetname);
						Row r1 = s1.getRow(6);
						//Row r1 = s1.createRow(6);
						Cell c1 = r1.createCell(1);
						//Cell c1 = r1.createCell(1);
						System.out.println("preauthNoooooo"+str);
						//c1.setCellValue("preauthNo");
						c1.setCellValue(str);
						FileOutputStream fos = new FileOutputStream("E:\\SVN-V3-automation\\Automation\\src\\main\\java\\com\\qa\\vidalhealth\\TestData\\"+Filename+".xlsx");
						wb.write(fos);
						
						/*String preAuthNo = "preAuthNowriting";
						testUtil.takeScreenshot(preAuthNo);*/
						
						fis.close();
						fos.close();
					}
					//	//write to EXCEL old code
					
					//write to EXCEL old code for data provider
					public void writeToExceloldcodeForDataProvider(String newDateTime, String Filename, String sheetname) throws Exception
					{
						System.out.println("newDate---nd"+newDateTime);
						//TestUtil testUtil = new TestUtil();
						FileInputStream fis = new FileInputStream("E:\\SVN-V3-automation\\Automation\\src\\main\\java\\com\\qa\\vidalhealth\\TestData\\"+Filename+".xlsx");
						Workbook wb = WorkbookFactory.create(fis);
						Sheet s1 = wb.getSheet(sheetname);
						Row r1 = s1.getRow(1);
						Row r2 = s1.getRow(2);
						Row r3 = s1.getRow(3);
						//Row r1 = s1.createRow(6);
						Cell c1 = r1.createCell(6);
						Cell c2 = r2.createCell(6);
						Cell c3 = r3.createCell(6);
						//Cell c1 = r1.createCell(1);
						System.out.println("preauthNoooooo"+newDateTime);
						//c1.setCellValue("preauthNo");
						c1.setCellValue(newDateTime);
						c2.setCellValue(newDateTime);
						c3.setCellValue(newDateTime);
						FileOutputStream fos = new FileOutputStream("E:\\SVN-V3-automation\\Automation\\src\\main\\java\\com\\qa\\vidalhealth\\TestData\\"+Filename+".xlsx");
						wb.write(fos);
						
						/*String preAuthNo = "preAuthNowriting";
						testUtil.takeScreenshot(preAuthNo);*/
						
						fis.close();
						fos.close();
					}
					//	//write to EXCEL old code for data provider
					
					
					//Read PA No from EXCEL
					public String readPAFromExcel() throws Exception
					{
						
						TestUtil testUtil = new TestUtil();
						FileInputStream fis = new FileInputStream("E:\\SVN-V3-automation\\Automation\\src\\main\\java\\com"
								+ "\\qa\\vidalhealth\\TestData\\PreauthNo.xlsx");
						Workbook wb = WorkbookFactory.create(fis);
						Sheet s1 = wb.getSheet("PreauthGED");
						//Row r1 = s1.getRow(0);
						Row r1 = s1.getRow(6);
						//Cell c1 = r1.getCell(0);
						Cell c2 = r1.getCell(1);
						//System.out.println("preauthNoooooo"+preauthNo);
						//c1.setCellValue("preauthNo");
						String preauthNo = c2.getStringCellValue();
						/*FileOutputStream fos = new FileOutputStream("E:\\old system's-D drive data\\AUTOMATIOM-06122018\\Maven automation project\\src"
								+ "\\main\\java\\com\\qa\\vidalhealth\\TestData\\PreauthNo.xlsx");
						wb.write(fos);*/
						
						/*String preAuthNo = "preAuthNowriting";
						testUtil.takeScreenshot(preAuthNo);
						*/
						fis.close();
						//fos.close();
						
						return preauthNo;
					}
					
					
					//Read PA No from EXCEL
					
//					//Read DOA  from EXCEL 
					public String readDOAFromExcel(String Filename,String SheetName) throws Exception
					{
						
						TestUtil testUtil = new TestUtil();
						FileInputStream fis = new FileInputStream("E:\\SVN-V3-automation\\Automation\\src\\main\\java\\com"
								+ "\\qa\\vidalhealth\\TestData\\"+Filename+".xlsx");
						Workbook wb = WorkbookFactory.create(fis);
						Sheet s1 = wb.getSheet(SheetName);
					
						Row r1 = s1.getRow(6);
					
						Cell c2 = r1.getCell(1);
					
						String dateOfAdmission = c2.getStringCellValue();
			
						fis.close();
				      //fos.close();
						
						return dateOfAdmission;
					}
					//	//Read DOA  from EXCEL 
					
					//Read DOA  from EXCEL for DATA provider
					public String readDOAFromExcelForDataProvider() throws Exception
					{
						
						TestUtil testUtil = new TestUtil();
						FileInputStream fis = new FileInputStream("E:\\SVN-V3-automation\\Automation\\src\\main\\java\\com"
								+ "\\qa\\vidalhealth\\TestData\\PreauthGEDPageTestDataUsingDP.xlsx");
						Workbook wb = WorkbookFactory.create(fis);
						Sheet s1 = wb.getSheet("Sheet1");
					
						Row r1 = s1.getRow(1);
					
						Cell c2 = r1.getCell(6);
					
						String dateOfAdmission = c2.getStringCellValue();
						//-----------------------------
						
					/*	Row r2 = s1.getRow(2);
						
						Cell c3 = r1.getCell(6);
					
						String dateOfAdmission2 = c3.getStringCellValue();
						//---------------------------
						
						Row r3 = s1.getRow(3);
						
						Cell c4 = r1.getCell(6);
					
						String dateOfAdmission3 = c4.getStringCellValue();*/
			
						fis.close();
				      //fos.close();
						
						return dateOfAdmission;
					}
					//	//Read DOA  from EXCEL for DATA provider
			
		}

		


	


