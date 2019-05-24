package com.hisun.FS;

import com.hisun.DD.*;
import com.hisun.SC.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class FSZTLEG {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm FSTMST_RD;
    DBParm FSTTXD_RD;
    DBParm DDTCCY_RD;
    String K_OBJ_SYSTEM = "ESBP";
    String K_SERV_CODE = "3008200000101";
    String WS_MSG_ID = " ";
    String WS_ERR_INFO = " ";
    String WS_PAY_NAME = " ";
    String WS_RES_NAME = " ";
    String WS_UPACC_NO = " ";
    double WS_AVL_AMT = 0;
    char WS_FT_FLAG = ' ';
    char WS_TBL_FLAG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    CICQACAC CICQACAC = new CICQACAC();
    SCCTPCL SCCTPCL = new SCCTPCL();
    FSCBTECP FSCBTECP = new FSCBTECP();
    FSRMST FSRMST = new FSRMST();
    FSRTXD FSRTXD = new FSRTXD();
    DDRCCY DDRCCY = new DDRCCY();
    SCCGWA SCCGWA;
    FSCTLEG FSCTLEG;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, FSCTLEG FSCTLEG) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.FSCTLEG = FSCTLEG;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "FSZTLEG return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, FSCBTECP);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_INFO();
        B040_DEP_PROC();
        B050_UPDATE_FSTMST_PROC();
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            B060_WRITE_FSTTXD_PROC();
        } else {
            B065_RECORD_TXD_CANCEL();
        }
        B070_NOTICE_BTE_SYS_PROC();
        B030_DRW_PROC();
    }
    public void B010_CHECK_INPUT_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FSRMST);
        FSRMST.KEY.ACC_NO = FSCTLEG.RES_ACCT;
        T000_READ_FSTMST();
        CEP.TRC(SCCGWA, FSRMST.UPACC_NO);
        WS_UPACC_NO = FSRMST.UPACC_NO;
        WS_FT_FLAG = FSRMST.FT_FLAG;
        if (FSRMST.PAY_FLAG == '0') {
        } else {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_PYMTH_EER_INPUT;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = FSCTLEG.RES_ACCT;
        CICQACAC.DATA.CCY_ACAC = FSCTLEG.CCY;
        CICQACAC.DATA.CR_FLG = FSCTLEG.CCY_TYP;
        S000_CALL_CIZQACAC();
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        WS_RES_NAME = CICQACAC.O_DATA.O_ACR_DATA.O_AC_CNM;
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        T000_READ_DDTCCY();
        if (WS_TBL_FLAG == 'N') {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
        CEP.TRC(SCCGWA, FSCTLEG.TRANS_ATM);
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = WS_UPACC_NO;
        CICQACAC.DATA.CCY_ACAC = FSCTLEG.CCY;
        CICQACAC.DATA.CR_FLG = FSCTLEG.CCY_TYP;
        S000_CALL_CIZQACAC();
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        WS_PAY_NAME = CICQACAC.O_DATA.O_ACR_DATA.O_AC_CNM;
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        T000_READ_DDTCCY();
        if (WS_TBL_FLAG == 'N') {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            WS_AVL_AMT = ( FSRMST.SS_BAL - FSRMST.XH_BAL + FSRMST.LIMIT_AMT );
            CEP.TRC(SCCGWA, FSCTLEG.TRANS_ATM);
            CEP.TRC(SCCGWA, WS_AVL_AMT);
            if (WS_AVL_AMT < FSCTLEG.TRANS_ATM 
                && FSCTLEG.LMT_FLG != '1') {
                WS_MSG_ID = DDCMSG_ERROR_MSG.DD_AVL_BAL_NOT_ENOUGH;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B030_DRW_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUDRAC);
        DDCUDRAC.TX_TYPE = 'T';
        DDCUDRAC.CHK_PSW_FLG = 'N';
        DDCUDRAC.AC = WS_UPACC_NO;
        DDCUDRAC.OTHER_AC = FSCTLEG.RES_ACCT;
        DDCUDRAC.CCY = FSCTLEG.CCY;
        DDCUDRAC.CCY_TYPE = FSCTLEG.CCY_TYP;
        DDCUDRAC.TX_MMO = FSCTLEG.ZY_CODE;
        DDCUDRAC.TX_AMT = FSCTLEG.TRANS_ATM;
        DDCUDRAC.VAL_DATE = FSCTLEG.TRAN_DATE;
        DDCUDRAC.REMARKS = FSCTLEG.REMARK;
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            if (DDCUDRAC.TRT_CTLW == null) DDCUDRAC.TRT_CTLW = "";
            JIBS_tmp_int = DDCUDRAC.TRT_CTLW.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) DDCUDRAC.TRT_CTLW += " ";
            DDCUDRAC.TRT_CTLW = DDCUDRAC.TRT_CTLW.substring(0, 2 - 1) + "1" + DDCUDRAC.TRT_CTLW.substring(2 + 1 - 1);
        }
        CEP.TRC(SCCGWA, FSCTLEG.SER_FLG);
        CEP.TRC(SCCGWA, WS_FT_FLAG);
        CEP.TRC(SCCGWA, FSCTLEG.FT_FLG);
        if (FSCTLEG.SER_FLG != '1') {
            if (DDCUDRAC.TRT_CTLW == null) DDCUDRAC.TRT_CTLW = "";
            JIBS_tmp_int = DDCUDRAC.TRT_CTLW.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) DDCUDRAC.TRT_CTLW += " ";
            JIBS_tmp_str[0] = "" + WS_FT_FLAG;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            DDCUDRAC.TRT_CTLW = DDCUDRAC.TRT_CTLW.substring(0, 3 - 1) + JIBS_tmp_str[0] + DDCUDRAC.TRT_CTLW.substring(3 + 1 - 1);
        } else {
            if (DDCUDRAC.TRT_CTLW == null) DDCUDRAC.TRT_CTLW = "";
            JIBS_tmp_int = DDCUDRAC.TRT_CTLW.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) DDCUDRAC.TRT_CTLW += " ";
            JIBS_tmp_str[0] = "" + FSCTLEG.FT_FLG;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            DDCUDRAC.TRT_CTLW = DDCUDRAC.TRT_CTLW.substring(0, 3 - 1) + JIBS_tmp_str[0] + DDCUDRAC.TRT_CTLW.substring(3 + 1 - 1);
        }
        if (DDCUDRAC.TRT_CTLW == null) DDCUDRAC.TRT_CTLW = "";
        JIBS_tmp_int = DDCUDRAC.TRT_CTLW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) DDCUDRAC.TRT_CTLW += " ";
        DDCUDRAC.TRT_CTLW = DDCUDRAC.TRT_CTLW.substring(0, 5 - 1) + "1" + DDCUDRAC.TRT_CTLW.substring(5 + 1 - 1);
        CEP.TRC(SCCGWA, DDCUDRAC.AC);
        CEP.TRC(SCCGWA, DDCUDRAC.CCY);
        CEP.TRC(SCCGWA, DDCUDRAC.CCY_TYPE);
        CEP.TRC(SCCGWA, DDCUDRAC.TX_AMT);
        S000_CALL_DDZUDRAC();
    }
    public void B040_DEP_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUCRAC);
        DDCUCRAC.TX_TYPE = 'T';
        DDCUCRAC.AC = FSCTLEG.RES_ACCT;
        DDCUCRAC.OTHER_AC = WS_UPACC_NO;
        DDCUCRAC.CCY = FSCTLEG.CCY;
        DDCUCRAC.CCY_TYPE = FSCTLEG.CCY_TYP;
        DDCUCRAC.TX_MMO = FSCTLEG.ZY_CODE;
        DDCUCRAC.TX_AMT = FSCTLEG.TRANS_ATM;
        DDCUCRAC.VAL_DATE = FSCTLEG.TRAN_DATE;
        DDCUCRAC.REMARKS = FSCTLEG.REMARK;
        if (DDCUCRAC.TRT_CTLW == null) DDCUCRAC.TRT_CTLW = "";
        JIBS_tmp_int = DDCUCRAC.TRT_CTLW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) DDCUCRAC.TRT_CTLW += " ";
        DDCUCRAC.TRT_CTLW = DDCUCRAC.TRT_CTLW.substring(0, 2 - 1) + "1" + DDCUCRAC.TRT_CTLW.substring(2 + 1 - 1);
        S000_CALL_DDZUCRAC();
    }
    public void B050_UPDATE_FSTMST_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FSRMST);
        FSRMST.KEY.ACC_NO = FSCTLEG.RES_ACCT;
        T000_READ_UPDATE_FSTMST();
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            FSRMST.XH_BAL = FSRMST.XH_BAL - FSCTLEG.TRANS_ATM;
        } else {
            FSRMST.XH_BAL = FSRMST.XH_BAL + FSCTLEG.TRANS_ATM;
        }
        if (FSRMST.SS_BAL >= FSRMST.XH_BAL) {
            FSRMST.LIMIT_USED = 0;
        } else {
            FSRMST.LIMIT_USED = FSRMST.XH_BAL - FSRMST.SS_BAL;
        }
        T000_REWRITE_FSTMST();
    }
    public void B060_WRITE_FSTTXD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FSRTXD);
        FSRTXD.KEY.TRAN_DATE = FSCTLEG.TRAN_DATE;
        FSRTXD.KEY.SERIAL_NO = SCCGWA.COMM_AREA.JRN_NO;
        FSRTXD.KEY.SUB_SERIAL_NO = SCCGWA.COMM_AREA.CALL_SEQ;
        FSRTXD.PAY_ACCT = WS_UPACC_NO;
        FSRTXD.RES_ACCT = FSCTLEG.RES_ACCT;
        FSRTXD.LT_ATM = 0;
        FSRTXD.CCY = FSCTLEG.CCY;
        FSRTXD.CCY_TYP = FSCTLEG.CCY_TYP;
        FSRTXD.TRIG_TYP = '0';
        FSRTXD.TYPE = "01";
        FSRTXD.TRANS_ATM = FSCTLEG.TRANS_ATM;
        FSRTXD.TRAN_TIME = SCCGWA.COMM_AREA.TR_TIME;
        FSRTXD.ZY_CODE = FSCTLEG.ZY_CODE;
        FSRTXD.REQFM_NO = " ";
        FSRTXD.EC_FLG = ' ';
        FSRTXD.REMARK = FSCTLEG.REMARK;
        FSRTXD.USE = FSCTLEG.USER;
        FSRTXD.OPP_ACCT = " ";
        FSRTXD.OPP_NME = " ";
        FSRTXD.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        FSRTXD.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        FSRTXD.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_FSTTXD();
    }
    public void B065_RECORD_TXD_CANCEL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FSRTXD);
        FSRTXD.KEY.TRAN_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        FSRTXD.KEY.SERIAL_NO = GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO;
        FSRTXD.KEY.SUB_SERIAL_NO = FSCTLEG.SUB_JRN_NO;
        CEP.TRC(SCCGWA, FSRTXD.KEY.TRAN_DATE);
        CEP.TRC(SCCGWA, FSRTXD.KEY.SERIAL_NO);
        CEP.TRC(SCCGWA, FSRTXD.KEY.SUB_SERIAL_NO);
        T000_READ_UPDATE_FSTTXD();
        FSRTXD.EC_FLG = 'Y';
        T000_REWRITE_FSTTXD();
    }
    public void B070_NOTICE_BTE_SYS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FSCBTECP);
        FSCBTECP.SERIAL_NO = SCCGWA.COMM_AREA.JRN_NO;
        FSCBTECP.SUB_SERIAL_NO = SCCGWA.COMM_AREA.CALL_SEQ;
        FSCBTECP.PAY_ACCT = WS_UPACC_NO;
        FSCBTECP.PAY_ACCT_NAME = WS_PAY_NAME;
        FSCBTECP.CRYTYPE = FSCTLEG.CCY;
        FSCBTECP.RES_ACCT = FSCTLEG.RES_ACCT;
        FSCBTECP.RES_ACCT_NAME = WS_RES_NAME;
        FSCBTECP.TYPE = '1';
        FSCBTECP.TRANS_AMT = FSCTLEG.TRANS_ATM;
        FSCBTECP.LT_AMT = 0;
        FSCBTECP.TRAN_DATE = FSCTLEG.TRAN_DATE;
        FSCBTECP.TRAN_TIME = SCCGWA.COMM_AREA.TR_TIME;
        FSCBTECP.ZY_CODE = FSCTLEG.ZY_CODE;
        FSCBTECP.REMARK = FSCTLEG.REMARK;
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            FSCBTECP.TRANS_STATUS = '1';
        } else {
            FSCBTECP.TRANS_STATUS = '0';
        }
        IBS.init(SCCGWA, SCCTPCL);
        SCCTPCL.SERV_AREA.OBJ_SYSTEM = K_OBJ_SYSTEM;
        SCCTPCL.SERV_AREA.SERV_CODE = K_SERV_CODE;
        SCCTPCL.SERV_AREA.SERV_TYPE = ' ';
        SCCTPCL.INP_AREA.SERV_DATA_LEN = 768;
        SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, FSCBTECP);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_CSIF_NOTICE);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_BVMS_INQ);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_BH_FILE_SEND);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_BH_FILE_ECIF);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_AC_CHG_INF);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ACGL_GL0002);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_PASSWORD_CHK);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_SMS_INFO);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ECIF_SG0001);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_CHG_NOTICE);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ACGL_GL0001);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ECIF_AC0001);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_LOAN_NOTICE);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.SN_SMS_NOTICE);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ECIF_AC0002);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.CC_CHANGE_CARD);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.SC_SCF_NOTICE);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ECIF_AC0003);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_IBIL_NOTICE);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_BROADCAST);
        IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_CASH_CHG);
        if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
            S000_CALL_SCZTPCL();
        }
    }
    public void T000_READ_FSTMST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, FSRMST.KEY.ACC_NO);
        FSTMST_RD = new DBParm();
        FSTMST_RD.TableName = "FSTMST";
        IBS.READ(SCCGWA, FSRMST, FSTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_NO_RSLT;
            S000_ERR_MSG_PROC();
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "READ TABLE FSTMST ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "FSTMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_READ_UPDATE_FSTMST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, FSRMST.KEY.ACC_NO);
        FSTMST_RD = new DBParm();
        FSTMST_RD.TableName = "FSTMST";
        FSTMST_RD.upd = true;
        IBS.READ(SCCGWA, FSRMST, FSTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_LPAY_REC_NOTFND;
            S000_ERR_MSG_PROC();
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "READ TABLE FSTMST ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "FSTMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_REWRITE_FSTMST() throws IOException,SQLException,Exception {
        FSRMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        FSRMST.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        FSRMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        FSTMST_RD = new DBParm();
        FSTMST_RD.TableName = "FSTMST";
        IBS.REWRITE(SCCGWA, FSRMST, FSTMST_RD);
    }
    public void T000_READ_UPDATE_FSTTXD() throws IOException,SQLException,Exception {
        FSTTXD_RD = new DBParm();
        FSTTXD_RD.TableName = "FSTTXD";
        FSTTXD_RD.upd = true;
        IBS.READ(SCCGWA, FSRTXD, FSTTXD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_LPAY_REC_NOTFND;
            S000_ERR_MSG_PROC();
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "READ TABLE FSTTXD ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "FSTTXD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_REWRITE_FSTTXD() throws IOException,SQLException,Exception {
        FSRTXD.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        FSRTXD.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        FSRTXD.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        FSTTXD_RD = new DBParm();
        FSTTXD_RD.TableName = "FSTTXD";
        IBS.REWRITE(SCCGWA, FSRTXD, FSTTXD_RD);
    }
    public void T000_WRITE_FSTTXD() throws IOException,SQLException,Exception {
        FSTTXD_RD = new DBParm();
        FSTTXD_RD.TableName = "FSTTXD";
        FSTTXD_RD.errhdl = true;
        IBS.WRITE(SCCGWA, FSRTXD, FSTTXD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_MST1_REC_EXIST;
            S000_ERR_MSG_PROC();
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "WRITE TABLE FSTTXD ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "FSTTXD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_READ_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTCCY";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE == 0) {
        } else {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DDZUCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DEP-PROC", DDCUCRAC);
    }
    public void S000_CALL_DDZUDRAC() throws IOException,SQLException,Exception {
        DDZUDRAC DDZUDRAC = new DDZUDRAC();
        DDZUDRAC.MP(SCCGWA, DDCUDRAC);
    }
    public void S000_CALL_SCZTPCL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-LINK-EXTSERV", SCCTPCL);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSG_ID, WS_ERR_INFO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
