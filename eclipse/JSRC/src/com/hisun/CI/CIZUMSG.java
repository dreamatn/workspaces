package com.hisun.CI;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class CIZUMSG {
    int MMSG1_BLOB_MSG_DATA_LEN;
    int MMSG2_BLOB_MSG_DATA_LEN;
    int MMSG3_BLOB_MSG_DATA_LEN;
    int MMSG4_BLOB_MSG_DATA_LEN;
    int MMSG5_BLOB_MSG_DATA_LEN;
    int MMSG6_BLOB_MSG_DATA_LEN;
    int MMSG7_BLOB_MSG_DATA_LEN;
    DBParm CITMMSG1_RD;
    DBParm CITMMSG2_RD;
    DBParm CITMMSG3_RD;
    DBParm CITMMSG4_RD;
    DBParm CITMMSG5_RD;
    DBParm CITMMSG6_RD;
    DBParm CITMMSG7_RD;
    DBParm CITBAS_RD;
    DBParm CITCNT_RD;
    DBParm CITCDM_RD;
    DBParm CITFDM_RD;
    String CPN_SCSSCKDT = "SCSSCKDT";
    short WS_WEEK_NO = 0;
    CIZUMSG_WS_RC WS_RC = new CIZUMSG_WS_RC();
    short WS_I = 0;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    CIRMMSG1 CIRMMSG1 = new CIRMMSG1();
    CIRMMSG2 CIRMMSG2 = new CIRMMSG2();
    CIRMMSG3 CIRMMSG3 = new CIRMMSG3();
    CIRMMSG4 CIRMMSG4 = new CIRMMSG4();
    CIRMMSG5 CIRMMSG5 = new CIRMMSG5();
    CIRMMSG6 CIRMMSG6 = new CIRMMSG6();
    CIRMMSG7 CIRMMSG7 = new CIRMMSG7();
    CIRBAS CIRBAS = new CIRBAS();
    CIRCNT CIRCNT = new CIRCNT();
    CIRCDM CIRCDM = new CIRCDM();
    CIRFDM CIRFDM = new CIRFDM();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICUMSG CICUMSG;
    public void MP(SCCGWA SCCGWA, CICUMSG CICUMSG) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICUMSG = CICUMSG;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIZUMSG return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICUMSG.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        for (WS_I = 1; WS_I <= 10 
            && CICUMSG.DATA[WS_I-1].CI_NO.trim().length() != 0; WS_I += 1) {
            if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
                CICUMSG.DATA[WS_I-1].AP_TYPE = GWA_SC_AREA.BSP_APTYPE;
                CICUMSG.DATA[WS_I-1].AP_BATNO = GWA_SC_AREA.BSP_BATNO;
            }
            CEP.TRC(SCCGWA, WS_I);
            CEP.TRC(SCCGWA, CICUMSG.DATA[WS_I-1].CI_NO);
            B010_CHECK_INPUT_DATA();
            if (CICUMSG.FUNC == 'A') {
                B020_WRITE_MSG();
            } else if (CICUMSG.FUNC == 'M') {
                B030_DEAL_MSG();
            } else {
                IBS.init(SCCGWA, SCCEXCP);
                SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INPUT FUNC ERROR";
                CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
            }
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_I);
        CEP.TRC(SCCGWA, CICUMSG.DATA[WS_I-1].CI_NO);
        CEP.TRC(SCCGWA, CICUMSG.DATA[WS_I-1].MSG.SYS_NO.AC_DATE);
        CEP.TRC(SCCGWA, CICUMSG.DATA[WS_I-1].MSG.SYS_NO.JRN_NO);
        CEP.TRC(SCCGWA, CICUMSG.DATA[WS_I-1].MSG.SYS_NO.JRN_SEQ);
        if (CICUMSG.DATA[WS_I-1].CI_NO.trim().length() == 0 
            || CICUMSG.DATA[WS_I-1].MSG.SYS_NO.AC_DATE == 0 
            || CICUMSG.DATA[WS_I-1].MSG.SYS_NO.JRN_NO.trim().length() == 0 
            || CICUMSG.DATA[WS_I-1].MSG.SYS_NO.JRN_SEQ == 0) {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "LACK OF NORMAL INFORMATION";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        CEP.TRC(SCCGWA, CICUMSG.DATA[WS_I-1].MSG.TEL_NO);
        if (CICUMSG.DATA[WS_I-1].MSG.TEL_NO.trim().length() == 0) {
            IBS.init(SCCGWA, CIRBAS);
            CIRBAS.KEY.CI_NO = CICUMSG.DATA[WS_I-1].CI_NO;
            T000_READ_CITBAS();
            if (CIRBAS.CI_TYP == '1') {
                IBS.init(SCCGWA, CIRCNT);
                CIRCNT.KEY.CI_NO = CIRBAS.KEY.CI_NO;
                CIRCNT.KEY.CNT_TYPE = "21";
                T000_READ_CITCNT();
                CICUMSG.DATA[WS_I-1].MSG.TEL_NO = CIRCNT.TEL_NO;
            } else if (CIRBAS.CI_TYP == '2') {
                IBS.init(SCCGWA, CIRCDM);
                CIRCDM.KEY.CI_NO = CIRBAS.KEY.CI_NO;
                T000_READ_CITCDM();
                if (CIRCDM.CONT1_MOB_NO.trim().length() > 0) {
                    CICUMSG.DATA[WS_I-1].MSG.TEL_NO = CIRCDM.CONT1_MOB_NO;
                } else if (CIRCDM.CONT2_MOB_NO.trim().length() > 0) {
                    CICUMSG.DATA[WS_I-1].MSG.TEL_NO = CIRCDM.CONT2_MOB_NO;
                } else if (CIRCDM.FIN_DIR_MOB_NO.trim().length() > 0) {
                    CICUMSG.DATA[WS_I-1].MSG.TEL_NO = CIRCDM.FIN_DIR_MOB_NO;
                } else {
                }
            } else if (CIRBAS.CI_TYP == '3') {
                IBS.init(SCCGWA, CIRFDM);
                CIRFDM.KEY.CI_NO = CIRBAS.KEY.CI_NO;
                T000_READ_CITFDM();
                if (CIRFDM.CONT1_MOB_NO.trim().length() > 0) {
                    CICUMSG.DATA[WS_I-1].MSG.TEL_NO = CIRFDM.CONT1_MOB_NO;
                } else if (CIRFDM.CONT2_MOB_NO.trim().length() > 0) {
                    CICUMSG.DATA[WS_I-1].MSG.TEL_NO = CIRFDM.CONT2_MOB_NO;
                } else if (CIRFDM.FIN_DIR_MOB_NO.trim().length() > 0) {
                    CICUMSG.DATA[WS_I-1].MSG.TEL_NO = CIRFDM.FIN_DIR_MOB_NO;
                } else {
                }
            } else {
            }
        }
    }
    public void B020_WRITE_MSG() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_I);
        CEP.TRC(SCCGWA, CICUMSG.DATA[WS_I-1].MSG.SYS_NO.AC_DATE);
        IBS.init(SCCGWA, SCCCKDT);
        SCCCKDT.DATE = CICUMSG.DATA[WS_I-1].MSG.SYS_NO.AC_DATE;
        S000_CALL_SCSSCKDT();
        CEP.TRC(SCCGWA, SCCCKDT.WEEK_NO);
        WS_WEEK_NO = SCCCKDT.WEEK_NO;
        if (WS_WEEK_NO == 1) {
            B021_WRITE_CITMMSG1();
        } else if (WS_WEEK_NO == 2) {
            B022_WRITE_CITMMSG2();
        } else if (WS_WEEK_NO == 3) {
            B023_WRITE_CITMMSG3();
        } else if (WS_WEEK_NO == 4) {
            B024_WRITE_CITMMSG4();
        } else if (WS_WEEK_NO == 5) {
            B025_WRITE_CITMMSG5();
        } else if (WS_WEEK_NO == 6) {
            B026_WRITE_CITMMSG6();
        } else if (WS_WEEK_NO == 7) {
            B027_WRITE_CITMMSG7();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "WEEK ERROR";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B021_WRITE_CITMMSG1() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRMMSG1);
        CIRMMSG1.KEY.AC_DATE = CICUMSG.DATA[WS_I-1].MSG.SYS_NO.AC_DATE;
        CIRMMSG1.KEY.JRN_NO = CICUMSG.DATA[WS_I-1].MSG.SYS_NO.JRN_NO;
        CIRMMSG1.KEY.JRN_SEQ = CICUMSG.DATA[WS_I-1].MSG.SYS_NO.JRN_SEQ;
        CIRMMSG1.KEY.SERV_CODE = CICUMSG.DATA[WS_I-1].SERV_CODE;
        CIRMMSG1.KEY.AP_TYPE = CICUMSG.DATA[WS_I-1].AP_TYPE;
        CIRMMSG1.AP_BATNO = CICUMSG.DATA[WS_I-1].AP_BATNO;
        CIRMMSG1.AP_CODE = CICUMSG.DATA[WS_I-1].AP_CODE;
        CIRMMSG1.REQ_SYS = CICUMSG.DATA[WS_I-1].REQ_SYS;
        CIRMMSG1.CI_NO = CICUMSG.DATA[WS_I-1].CI_NO;
        CIRMMSG1.BLOB_MSG_DATA = IBS.CLS2CPY(SCCGWA, CICUMSG.DATA[WS_I-1].MSG);
        MMSG1_BLOB_MSG_DATA_LEN = CIRMMSG1.BLOB_MSG_DATA.length();
        CIRMMSG1.DEAL_FLG = '0';
        T000_WRITE_CITMMSG1();
    }
    public void B022_WRITE_CITMMSG2() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRMMSG2);
        CIRMMSG2.KEY.AC_DATE = CICUMSG.DATA[WS_I-1].MSG.SYS_NO.AC_DATE;
        CIRMMSG2.KEY.JRN_NO = CICUMSG.DATA[WS_I-1].MSG.SYS_NO.JRN_NO;
        CIRMMSG2.KEY.JRN_SEQ = CICUMSG.DATA[WS_I-1].MSG.SYS_NO.JRN_SEQ;
        CIRMMSG2.KEY.SERV_CODE = CICUMSG.DATA[WS_I-1].SERV_CODE;
        CIRMMSG2.KEY.AP_TYPE = CICUMSG.DATA[WS_I-1].AP_TYPE;
        CIRMMSG2.AP_BATNO = CICUMSG.DATA[WS_I-1].AP_BATNO;
        CIRMMSG2.AP_CODE = CICUMSG.DATA[WS_I-1].AP_CODE;
        CIRMMSG2.REQ_SYS = CICUMSG.DATA[WS_I-1].REQ_SYS;
        CIRMMSG2.CI_NO = CICUMSG.DATA[WS_I-1].CI_NO;
        CIRMMSG2.BLOB_MSG_DATA = IBS.CLS2CPY(SCCGWA, CICUMSG.DATA[WS_I-1].MSG);
        MMSG2_BLOB_MSG_DATA_LEN = CIRMMSG2.BLOB_MSG_DATA.length();
        CIRMMSG2.DEAL_FLG = '0';
        T000_WRITE_CITMMSG2();
    }
    public void B023_WRITE_CITMMSG3() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRMMSG3);
        CIRMMSG3.KEY.AC_DATE = CICUMSG.DATA[WS_I-1].MSG.SYS_NO.AC_DATE;
        CIRMMSG3.KEY.JRN_NO = CICUMSG.DATA[WS_I-1].MSG.SYS_NO.JRN_NO;
        CIRMMSG3.KEY.JRN_SEQ = CICUMSG.DATA[WS_I-1].MSG.SYS_NO.JRN_SEQ;
        CIRMMSG3.KEY.SERV_CODE = CICUMSG.DATA[WS_I-1].SERV_CODE;
        CIRMMSG3.KEY.AP_TYPE = CICUMSG.DATA[WS_I-1].AP_TYPE;
        CIRMMSG3.AP_BATNO = CICUMSG.DATA[WS_I-1].AP_BATNO;
        CIRMMSG3.AP_CODE = CICUMSG.DATA[WS_I-1].AP_CODE;
        CIRMMSG3.REQ_SYS = CICUMSG.DATA[WS_I-1].REQ_SYS;
        CIRMMSG3.CI_NO = CICUMSG.DATA[WS_I-1].CI_NO;
        CIRMMSG3.BLOB_MSG_DATA = IBS.CLS2CPY(SCCGWA, CICUMSG.DATA[WS_I-1].MSG);
        MMSG3_BLOB_MSG_DATA_LEN = CIRMMSG3.BLOB_MSG_DATA.length();
        CIRMMSG3.DEAL_FLG = '0';
        T000_WRITE_CITMMSG3();
    }
    public void B024_WRITE_CITMMSG4() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRMMSG4);
        CIRMMSG4.KEY.AC_DATE = CICUMSG.DATA[WS_I-1].MSG.SYS_NO.AC_DATE;
        CIRMMSG4.KEY.JRN_NO = CICUMSG.DATA[WS_I-1].MSG.SYS_NO.JRN_NO;
        CIRMMSG4.KEY.JRN_SEQ = CICUMSG.DATA[WS_I-1].MSG.SYS_NO.JRN_SEQ;
        CIRMMSG4.KEY.SERV_CODE = CICUMSG.DATA[WS_I-1].SERV_CODE;
        CIRMMSG4.KEY.AP_TYPE = CICUMSG.DATA[WS_I-1].AP_TYPE;
        CIRMMSG4.AP_BATNO = CICUMSG.DATA[WS_I-1].AP_BATNO;
        CIRMMSG4.AP_CODE = CICUMSG.DATA[WS_I-1].AP_CODE;
        CIRMMSG4.REQ_SYS = CICUMSG.DATA[WS_I-1].REQ_SYS;
        CIRMMSG4.CI_NO = CICUMSG.DATA[WS_I-1].CI_NO;
        CIRMMSG4.BLOB_MSG_DATA = IBS.CLS2CPY(SCCGWA, CICUMSG.DATA[WS_I-1].MSG);
        MMSG4_BLOB_MSG_DATA_LEN = CIRMMSG4.BLOB_MSG_DATA.length();
        CIRMMSG4.DEAL_FLG = '0';
        T000_WRITE_CITMMSG4();
    }
    public void B025_WRITE_CITMMSG5() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRMMSG5);
        CIRMMSG5.KEY.AC_DATE = CICUMSG.DATA[WS_I-1].MSG.SYS_NO.AC_DATE;
        CIRMMSG5.KEY.JRN_NO = CICUMSG.DATA[WS_I-1].MSG.SYS_NO.JRN_NO;
        CIRMMSG5.KEY.JRN_SEQ = CICUMSG.DATA[WS_I-1].MSG.SYS_NO.JRN_SEQ;
        CIRMMSG5.KEY.SERV_CODE = CICUMSG.DATA[WS_I-1].SERV_CODE;
        CIRMMSG5.KEY.AP_TYPE = CICUMSG.DATA[WS_I-1].AP_TYPE;
        CIRMMSG5.AP_BATNO = CICUMSG.DATA[WS_I-1].AP_BATNO;
        CIRMMSG5.AP_CODE = CICUMSG.DATA[WS_I-1].AP_CODE;
        CIRMMSG5.REQ_SYS = CICUMSG.DATA[WS_I-1].REQ_SYS;
        CIRMMSG5.CI_NO = CICUMSG.DATA[WS_I-1].CI_NO;
        CIRMMSG5.BLOB_MSG_DATA = IBS.CLS2CPY(SCCGWA, CICUMSG.DATA[WS_I-1].MSG);
        MMSG5_BLOB_MSG_DATA_LEN = CIRMMSG5.BLOB_MSG_DATA.length();
        CIRMMSG5.DEAL_FLG = '0';
        T000_WRITE_CITMMSG5();
        CEP.TRC(SCCGWA, CIRMMSG5.BLOB_MSG_DATA);
    }
    public void B026_WRITE_CITMMSG6() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRMMSG6);
        CIRMMSG6.KEY.AC_DATE = CICUMSG.DATA[WS_I-1].MSG.SYS_NO.AC_DATE;
        CIRMMSG6.KEY.JRN_NO = CICUMSG.DATA[WS_I-1].MSG.SYS_NO.JRN_NO;
        CIRMMSG6.KEY.JRN_SEQ = CICUMSG.DATA[WS_I-1].MSG.SYS_NO.JRN_SEQ;
        CIRMMSG6.KEY.SERV_CODE = CICUMSG.DATA[WS_I-1].SERV_CODE;
        CIRMMSG6.KEY.AP_TYPE = CICUMSG.DATA[WS_I-1].AP_TYPE;
        CIRMMSG6.AP_BATNO = CICUMSG.DATA[WS_I-1].AP_BATNO;
        CIRMMSG6.AP_CODE = CICUMSG.DATA[WS_I-1].AP_CODE;
        CIRMMSG6.REQ_SYS = CICUMSG.DATA[WS_I-1].REQ_SYS;
        CIRMMSG6.CI_NO = CICUMSG.DATA[WS_I-1].CI_NO;
        CIRMMSG6.BLOB_MSG_DATA = IBS.CLS2CPY(SCCGWA, CICUMSG.DATA[WS_I-1].MSG);
        MMSG6_BLOB_MSG_DATA_LEN = CIRMMSG6.BLOB_MSG_DATA.length();
        CIRMMSG6.DEAL_FLG = '0';
        T000_WRITE_CITMMSG6();
    }
    public void B027_WRITE_CITMMSG7() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRMMSG7);
        CIRMMSG7.KEY.AC_DATE = CICUMSG.DATA[WS_I-1].MSG.SYS_NO.AC_DATE;
        CIRMMSG7.KEY.JRN_NO = CICUMSG.DATA[WS_I-1].MSG.SYS_NO.JRN_NO;
        CIRMMSG7.KEY.JRN_SEQ = CICUMSG.DATA[WS_I-1].MSG.SYS_NO.JRN_SEQ;
        CIRMMSG7.KEY.SERV_CODE = CICUMSG.DATA[WS_I-1].SERV_CODE;
        CIRMMSG7.KEY.AP_TYPE = CICUMSG.DATA[WS_I-1].AP_TYPE;
        CIRMMSG7.AP_BATNO = CICUMSG.DATA[WS_I-1].AP_BATNO;
        CIRMMSG7.AP_CODE = CICUMSG.DATA[WS_I-1].AP_CODE;
        CIRMMSG7.REQ_SYS = CICUMSG.DATA[WS_I-1].REQ_SYS;
        CIRMMSG7.CI_NO = CICUMSG.DATA[WS_I-1].CI_NO;
        CIRMMSG7.BLOB_MSG_DATA = IBS.CLS2CPY(SCCGWA, CICUMSG.DATA[WS_I-1].MSG);
        MMSG7_BLOB_MSG_DATA_LEN = CIRMMSG7.BLOB_MSG_DATA.length();
        CIRMMSG7.DEAL_FLG = '0';
        T000_WRITE_CITMMSG7();
    }
    public void B030_DEAL_MSG() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_I);
        CEP.TRC(SCCGWA, CICUMSG.DATA[WS_I-1].MSG.SYS_NO.AC_DATE);
        IBS.init(SCCGWA, SCCCKDT);
        SCCCKDT.DATE = CICUMSG.DATA[WS_I-1].MSG.SYS_NO.AC_DATE;
        S000_CALL_SCSSCKDT();
        CEP.TRC(SCCGWA, SCCCKDT.WEEK_NO);
        WS_WEEK_NO = SCCCKDT.WEEK_NO;
        if (WS_WEEK_NO == 1) {
            B031_DEAL_CITMMSG1();
        } else if (WS_WEEK_NO == 2) {
            B032_DEAL_CITMMSG2();
        } else if (WS_WEEK_NO == 3) {
            B033_DEAL_CITMMSG3();
        } else if (WS_WEEK_NO == 4) {
            B034_DEAL_CITMMSG4();
        } else if (WS_WEEK_NO == 5) {
            B035_DEAL_CITMMSG5();
        } else if (WS_WEEK_NO == 6) {
            B036_DEAL_CITMMSG6();
        } else if (WS_WEEK_NO == 7) {
            B037_DEAL_CITMMSG7();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "WEEK ERROR";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B031_DEAL_CITMMSG1() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRMMSG1);
        CIRMMSG1.KEY.AC_DATE = CICUMSG.DATA[WS_I-1].MSG.SYS_NO.AC_DATE;
        CIRMMSG1.KEY.JRN_NO = CICUMSG.DATA[WS_I-1].MSG.SYS_NO.JRN_NO;
        CIRMMSG1.KEY.JRN_SEQ = CICUMSG.DATA[WS_I-1].MSG.SYS_NO.JRN_SEQ;
        T000_READ_CITMMSG1_UPD();
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CIRMMSG1.DEAL_FLG = '1';
            T000_REWRITE_CITMMSG1();
        }
    }
    public void B032_DEAL_CITMMSG2() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRMMSG2);
        CIRMMSG2.KEY.AC_DATE = CICUMSG.DATA[WS_I-1].MSG.SYS_NO.AC_DATE;
        CIRMMSG2.KEY.JRN_NO = CICUMSG.DATA[WS_I-1].MSG.SYS_NO.JRN_NO;
        CIRMMSG2.KEY.JRN_SEQ = CICUMSG.DATA[WS_I-1].MSG.SYS_NO.JRN_SEQ;
        T000_READ_CITMMSG2_UPD();
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CIRMMSG2.DEAL_FLG = '1';
            T000_REWRITE_CITMMSG2();
        }
    }
    public void B033_DEAL_CITMMSG3() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRMMSG3);
        CIRMMSG3.KEY.AC_DATE = CICUMSG.DATA[WS_I-1].MSG.SYS_NO.AC_DATE;
        CIRMMSG3.KEY.JRN_NO = CICUMSG.DATA[WS_I-1].MSG.SYS_NO.JRN_NO;
        CIRMMSG3.KEY.JRN_SEQ = CICUMSG.DATA[WS_I-1].MSG.SYS_NO.JRN_SEQ;
        T000_READ_CITMMSG3_UPD();
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CIRMMSG3.DEAL_FLG = '1';
            T000_REWRITE_CITMMSG3();
        }
    }
    public void B034_DEAL_CITMMSG4() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRMMSG4);
        CIRMMSG4.KEY.AC_DATE = CICUMSG.DATA[WS_I-1].MSG.SYS_NO.AC_DATE;
        CIRMMSG4.KEY.JRN_NO = CICUMSG.DATA[WS_I-1].MSG.SYS_NO.JRN_NO;
        CIRMMSG4.KEY.JRN_SEQ = CICUMSG.DATA[WS_I-1].MSG.SYS_NO.JRN_SEQ;
        T000_READ_CITMMSG4_UPD();
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CIRMMSG4.DEAL_FLG = '1';
            T000_REWRITE_CITMMSG4();
        }
    }
    public void B035_DEAL_CITMMSG5() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRMMSG5);
        CIRMMSG5.KEY.AC_DATE = CICUMSG.DATA[WS_I-1].MSG.SYS_NO.AC_DATE;
        CIRMMSG5.KEY.JRN_NO = CICUMSG.DATA[WS_I-1].MSG.SYS_NO.JRN_NO;
        CIRMMSG5.KEY.JRN_SEQ = CICUMSG.DATA[WS_I-1].MSG.SYS_NO.JRN_SEQ;
        T000_READ_CITMMSG5_UPD();
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CIRMMSG5.DEAL_FLG = '1';
            T000_REWRITE_CITMMSG5();
        }
    }
    public void B036_DEAL_CITMMSG6() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRMMSG6);
        CIRMMSG6.KEY.AC_DATE = CICUMSG.DATA[WS_I-1].MSG.SYS_NO.AC_DATE;
        CIRMMSG6.KEY.JRN_NO = CICUMSG.DATA[WS_I-1].MSG.SYS_NO.JRN_NO;
        CIRMMSG6.KEY.JRN_SEQ = CICUMSG.DATA[WS_I-1].MSG.SYS_NO.JRN_SEQ;
        T000_READ_CITMMSG6_UPD();
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CIRMMSG6.DEAL_FLG = '1';
            T000_REWRITE_CITMMSG6();
        }
    }
    public void B037_DEAL_CITMMSG7() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRMMSG7);
        CIRMMSG7.KEY.AC_DATE = CICUMSG.DATA[WS_I-1].MSG.SYS_NO.AC_DATE;
        CIRMMSG7.KEY.JRN_NO = CICUMSG.DATA[WS_I-1].MSG.SYS_NO.JRN_NO;
        CIRMMSG7.KEY.JRN_SEQ = CICUMSG.DATA[WS_I-1].MSG.SYS_NO.JRN_SEQ;
        T000_READ_CITMMSG7_UPD();
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CIRMMSG7.DEAL_FLG = '1';
            T000_REWRITE_CITMMSG7();
        }
    }
    public void S000_CALL_SCSSCKDT() throws IOException,SQLException,Exception {
        SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
        WS_RC.WS_CODE = 0;
        SCSSCKDT1.MP(SCCGWA, SCCCKDT);
        CEP.TRC(SCCGWA, WS_RC.WS_CODE);
        WS_RC.WS_MMO = "SC";
        if (WS_RC.WS_CODE != 0) {
            CEP.ERR(SCCGWA, WS_RC);
        }
    }
    public void T000_WRITE_CITMMSG1() throws IOException,SQLException,Exception {
        CITMMSG1_RD = new DBParm();
        CITMMSG1_RD.TableName = "CITMMSG1";
        IBS.WRITE(SCCGWA, CIRMMSG1, CITMMSG1_RD);
    }
    public void T000_READ_CITMMSG1_UPD() throws IOException,SQLException,Exception {
        CITMMSG1_RD = new DBParm();
        CITMMSG1_RD.TableName = "CITMMSG1";
        CITMMSG1_RD.eqWhere = "AC_DATE , JRN_NO , JRN_SEQ";
        CITMMSG1_RD.upd = true;
        IBS.READ(SCCGWA, CIRMMSG1, CITMMSG1_RD);
    }
    public void T000_REWRITE_CITMMSG1() throws IOException,SQLException,Exception {
        CITMMSG1_RD = new DBParm();
        CITMMSG1_RD.TableName = "CITMMSG1";
        IBS.REWRITE(SCCGWA, CIRMMSG1, CITMMSG1_RD);
    }
    public void T000_WRITE_CITMMSG2() throws IOException,SQLException,Exception {
        CITMMSG2_RD = new DBParm();
        CITMMSG2_RD.TableName = "CITMMSG2";
        IBS.WRITE(SCCGWA, CIRMMSG2, CITMMSG2_RD);
    }
    public void T000_READ_CITMMSG2_UPD() throws IOException,SQLException,Exception {
        CITMMSG2_RD = new DBParm();
        CITMMSG2_RD.TableName = "CITMMSG2";
        CITMMSG2_RD.eqWhere = "AC_DATE , JRN_NO , JRN_SEQ";
        CITMMSG2_RD.upd = true;
        IBS.READ(SCCGWA, CIRMMSG2, CITMMSG2_RD);
    }
    public void T000_REWRITE_CITMMSG2() throws IOException,SQLException,Exception {
        CITMMSG2_RD = new DBParm();
        CITMMSG2_RD.TableName = "CITMMSG2";
        IBS.REWRITE(SCCGWA, CIRMMSG2, CITMMSG2_RD);
    }
    public void T000_WRITE_CITMMSG3() throws IOException,SQLException,Exception {
        CITMMSG3_RD = new DBParm();
        CITMMSG3_RD.TableName = "CITMMSG3";
        IBS.WRITE(SCCGWA, CIRMMSG3, CITMMSG3_RD);
    }
    public void T000_READ_CITMMSG3_UPD() throws IOException,SQLException,Exception {
        CITMMSG3_RD = new DBParm();
        CITMMSG3_RD.TableName = "CITMMSG3";
        CITMMSG3_RD.eqWhere = "AC_DATE , JRN_NO , JRN_SEQ";
        CITMMSG3_RD.upd = true;
        IBS.READ(SCCGWA, CIRMMSG3, CITMMSG3_RD);
    }
    public void T000_REWRITE_CITMMSG3() throws IOException,SQLException,Exception {
        CITMMSG3_RD = new DBParm();
        CITMMSG3_RD.TableName = "CITMMSG3";
        IBS.REWRITE(SCCGWA, CIRMMSG3, CITMMSG3_RD);
    }
    public void T000_WRITE_CITMMSG4() throws IOException,SQLException,Exception {
        CITMMSG4_RD = new DBParm();
        CITMMSG4_RD.TableName = "CITMMSG4";
        IBS.WRITE(SCCGWA, CIRMMSG4, CITMMSG4_RD);
    }
    public void T000_READ_CITMMSG4_UPD() throws IOException,SQLException,Exception {
        CITMMSG4_RD = new DBParm();
        CITMMSG4_RD.TableName = "CITMMSG4";
        CITMMSG4_RD.eqWhere = "AC_DATE , JRN_NO , JRN_SEQ";
        CITMMSG4_RD.upd = true;
        IBS.READ(SCCGWA, CIRMMSG4, CITMMSG4_RD);
    }
    public void T000_REWRITE_CITMMSG4() throws IOException,SQLException,Exception {
        CITMMSG4_RD = new DBParm();
        CITMMSG4_RD.TableName = "CITMMSG4";
        IBS.REWRITE(SCCGWA, CIRMMSG4, CITMMSG4_RD);
    }
    public void T000_WRITE_CITMMSG5() throws IOException,SQLException,Exception {
        CITMMSG5_RD = new DBParm();
        CITMMSG5_RD.TableName = "CITMMSG5";
        IBS.WRITE(SCCGWA, CIRMMSG5, CITMMSG5_RD);
    }
    public void T000_READ_CITMMSG5_UPD() throws IOException,SQLException,Exception {
        CITMMSG5_RD = new DBParm();
        CITMMSG5_RD.TableName = "CITMMSG5";
        CITMMSG5_RD.eqWhere = "AC_DATE , JRN_NO , JRN_SEQ";
        CITMMSG5_RD.upd = true;
        IBS.READ(SCCGWA, CIRMMSG5, CITMMSG5_RD);
    }
    public void T000_REWRITE_CITMMSG5() throws IOException,SQLException,Exception {
        CITMMSG5_RD = new DBParm();
        CITMMSG5_RD.TableName = "CITMMSG5";
        IBS.REWRITE(SCCGWA, CIRMMSG5, CITMMSG5_RD);
    }
    public void T000_WRITE_CITMMSG6() throws IOException,SQLException,Exception {
        CITMMSG6_RD = new DBParm();
        CITMMSG6_RD.TableName = "CITMMSG6";
        IBS.WRITE(SCCGWA, CIRMMSG6, CITMMSG6_RD);
    }
    public void T000_READ_CITMMSG6_UPD() throws IOException,SQLException,Exception {
        CITMMSG6_RD = new DBParm();
        CITMMSG6_RD.TableName = "CITMMSG6";
        CITMMSG6_RD.eqWhere = "AC_DATE , JRN_NO , JRN_SEQ";
        CITMMSG6_RD.upd = true;
        IBS.READ(SCCGWA, CIRMMSG6, CITMMSG6_RD);
    }
    public void T000_REWRITE_CITMMSG6() throws IOException,SQLException,Exception {
        CITMMSG6_RD = new DBParm();
        CITMMSG6_RD.TableName = "CITMMSG6";
        IBS.REWRITE(SCCGWA, CIRMMSG6, CITMMSG6_RD);
    }
    public void T000_WRITE_CITMMSG7() throws IOException,SQLException,Exception {
        CITMMSG7_RD = new DBParm();
        CITMMSG7_RD.TableName = "CITMMSG7";
        IBS.WRITE(SCCGWA, CIRMMSG7, CITMMSG7_RD);
    }
    public void T000_READ_CITMMSG7_UPD() throws IOException,SQLException,Exception {
        CITMMSG7_RD = new DBParm();
        CITMMSG7_RD.TableName = "CITMMSG7";
        CITMMSG7_RD.eqWhere = "AC_DATE , JRN_NO , JRN_SEQ";
        CITMMSG7_RD.upd = true;
        IBS.READ(SCCGWA, CIRMMSG7, CITMMSG7_RD);
    }
    public void T000_REWRITE_CITMMSG7() throws IOException,SQLException,Exception {
        CITMMSG7_RD = new DBParm();
        CITMMSG7_RD.TableName = "CITMMSG7";
        IBS.REWRITE(SCCGWA, CIRMMSG7, CITMMSG7_RD);
    }
    public void T000_READ_CITBAS() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
    }
    public void T000_READ_CITCNT() throws IOException,SQLException,Exception {
        CITCNT_RD = new DBParm();
        CITCNT_RD.TableName = "CITCNT";
        IBS.READ(SCCGWA, CIRCNT, CITCNT_RD);
    }
    public void T000_READ_CITCDM() throws IOException,SQLException,Exception {
        CITCDM_RD = new DBParm();
        CITCDM_RD.TableName = "CITCDM";
        IBS.READ(SCCGWA, CIRCDM, CITCDM_RD);
    }
    public void T000_READ_CITFDM() throws IOException,SQLException,Exception {
        CITFDM_RD = new DBParm();
        CITFDM_RD.TableName = "CITFDM";
        IBS.READ(SCCGWA, CIRFDM, CITFDM_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
