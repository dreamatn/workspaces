package com.hisun.FS;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.DD.*;
import com.hisun.CI.*;
import com.hisun.DC.*;

import java.io.IOException;
import java.sql.SQLException;

public class FSZFOPVS {
    int JIBS_tmp_int;
    DBParm FSTVMST_RD;
    DBParm DDTMST_RD;
    DBParm DDTCCY_RD;
    String CCI_INQ_ACCU = "CI-INQ-ACCU";
    String CDD_U_BPZPNHIS = "BP-REC-NHIS";
    String CFS_C_VIRTUAL_AC = "FS-C-VIRTUAL-AC";
    String K_OUTPUT_FMT = "FS510";
    String WS_MSG_ID = " ";
    String WS_ERR_INFO = " ";
    char WS_AC_STS = ' ';
    String WS_AC_STS_WORD = " ";
    int WS_VS_SEQ = 0;
    String WS_CI_NO = " ";
    String WS_PROD_CD = " ";
    char WS_AC_TYPE = ' ';
    char WS_TBL_FLAG = ' ';
    SCCCLDT SCCCLDT = new SCCCLDT();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    CICACCU CICACCU = new CICACCU();
    BPCCGAC BPCCGAC = new BPCCGAC();
    DCCIPACI DCCIPACI = new DCCIPACI();
    CICQACRI CICQACRI = new CICQACRI();
    CICQACAC CICQACAC = new CICQACAC();
    CICSACRL CICSACRL = new CICSACRL();
    CICSACR CICSACR = new CICSACR();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    DDCUOPAC DDCUOPAC = new DDCUOPAC();
    FSCOOPVS FSCOOPVS = new FSCOOPVS();
    DDCIMCYY DDCIMCYY = new DDCIMCYY();
    DDCSCPLC DDCSCPLC = new DDCSCPLC();
    BPCSOCAC BPCSOCAC = new BPCSOCAC();
    CICCUST CICCUST = new CICCUST();
    FSCFCLVS FSCFCLVS = new FSCFCLVS();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    DDRMST DDRMST = new DDRMST();
    DDRCCY DDRCCY = new DDRCCY();
    DCRHLD DCRHLD = new DCRHLD();
    FSRVMST FSRVMST = new FSRVMST();
    int WS_COUNT = 0;
    SCCGWA SCCGWA;
    BPCPORUP_DATA_INFO BPCPORUP;
    FSCFOPVS FSCFOPVS;
    public void MP(SCCGWA SCCGWA, FSCFOPVS FSCFOPVS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.FSCFOPVS = FSCFOPVS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "FSZFOPVS return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INFO();
        if (FSCFOPVS.ACTION_TYPE == 'A') {
            B030_UPD_REF();
            B025_CREATE_ACO_NO();
            B055_WRT_FSTVMST_PROC();
            B020_COUNT_FSTVMST();
            if (WS_AC_TYPE != '7' 
                && WS_COUNT == 1) {
                B060_SET_STSW_PROC();
            }
        } else if (FSCFOPVS.ACTION_TYPE == 'U') {
            B040_UPDATE_FSTVMST_PROC();
        } else if (FSCFOPVS.ACTION_TYPE == 'D') {
            B020_COUNT_FSTVMST();
            B045_CLOSE_VSAC_PROC();
            if (WS_AC_TYPE != '7' 
                && WS_COUNT == 1) {
                B065_CAL_STSW_PROC();
            }
        } else {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_FUNC_TYPE_ERR;
            S000_ERR_MSG_PROC();
        }
        B050_BP_NFHIS();
        B070_SET_RES();
    }
    public void B010_CHECK_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'C';
        CICQACAC.DATA.AGR_NO = FSCFOPVS.UPACC_NO;
        CICQACAC.DATA.CCY_ACAC = FSCFOPVS.CCY;
        CICQACAC.DATA.CR_FLG = FSCFOPVS.CCY_TYP;
        CEP.TRC(SCCGWA, FSCFOPVS.UPACC_NO);
        CEP.TRC(SCCGWA, FSCFOPVS.CCY);
        CEP.TRC(SCCGWA, FSCFOPVS.CCY_TYP);
        S000_CALL_CIZQACAC();
        CEP.TRC(SCCGWA, CICQACAC.DATA.AGR_NO);
        CEP.TRC(SCCGWA, CICQACAC.DATA.CCY_ACAC);
        CEP.TRC(SCCGWA, CICQACAC.DATA.CR_FLG);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = FSCFOPVS.UPACC_NO;
        T000_READ_DDTMST();
        if (WS_TBL_FLAG == 'N') {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        T000_READ_DDTCCY();
        WS_PROD_CD = DDRCCY.PROD_CODE;
        WS_AC_TYPE = DDRCCY.AC_TYPE;
        CEP.TRC(SCCGWA, WS_PROD_CD);
        if (WS_TBL_FLAG == 'N') {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
        WS_AC_STS = DDRMST.AC_STS;
        CEP.TRC(SCCGWA, WS_AC_STS);
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1") 
            || DDRCCY.STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_JSZM_AC_FBID;
            S000_ERR_MSG_PROC();
        }
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
            || DDRCCY.STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_AC_FR_NOT_ALL;
            S000_ERR_MSG_PROC();
        }
        if (WS_AC_STS == 'C') {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
            S000_ERR_MSG_PROC();
        }
        if (FSCFOPVS.ACTION_TYPE == 'A' 
            || FSCFOPVS.ACTION_TYPE == 'U') {
            if (WS_AC_STS == 'D') {
                WS_MSG_ID = DDCMSG_ERROR_MSG.DD_AC_L_HANGED;
                S000_ERR_MSG_PROC();
            }
            if (WS_AC_STS == 'M') {
                WS_MSG_ID = DDCMSG_ERROR_MSG.DD_AC_HAND;
                S000_ERR_MSG_PROC();
            }
        }
        if (FSCFOPVS.ACTION_TYPE == 'A') {
            if (WS_AC_STS == 'V') {
                WS_MSG_ID = DDCMSG_ERROR_MSG.DD_INACTIVE_AC;
                S000_ERR_MSG_PROC();
            }
            if (WS_AC_STS == 'O') {
                WS_MSG_ID = DDCMSG_ERROR_MSG.DD_INACTIVE_AC;
                S000_ERR_MSG_PROC();
            }
        }
        if (FSCFOPVS.ACTION_TYPE == 'D') {
        }
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = FSCFOPVS.UPACC_NO;
        S000_CALL_CIZACCU();
        WS_CI_NO = CICACCU.DATA.CI_NO;
    }
    public void B025_CREATE_ACO_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUOPAC);
        DDCUOPAC.CI_NO = CICACCU.DATA.CI_NO;
        DDCUOPAC.PROD_CODE = WS_PROD_CD;
        DDCUOPAC.CCY = FSCFOPVS.CCY;
        DDCUOPAC.CCY_TYPE = FSCFOPVS.CCY_TYP;
        DDCUOPAC.PSBK_FLG = '2';
        DDCUOPAC.CARD_FLG = '2';
        DDCUOPAC.DRAW_MTH = '5';
        DDCUOPAC.AC = FSCFOPVS.ACC_NO;
        DDCUOPAC.ACNO_FLG = 'D';
        DDCUOPAC.ACO_AC_TYP = '5';
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = FSCFOPVS.UPACC_NO;
        T000_READ_DDTMST();
        DDCUOPAC.CUS_MGR = " ";
        DDCUOPAC.REG_CENT = DDRMST.AC_OIC_CODE;
        DDCUOPAC.SUB_BIZ = DDRMST.SUB_DP;
        DDCUOPAC.AC_TYP = DDRMST.AC_TYPE;
        S000_CALL_DDZUOPAC();
    }
    public void B030_UPD_REF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSACR);
        CICSACR.FUNC = 'A';
        CICSACR.DATA.AGR_NO = FSCFOPVS.ACC_NO;
        CICSACR.DATA.ENTY_TYP = '4';
        CICSACR.DATA.CI_NO = WS_CI_NO;
        CICSACR.DATA.STSW = "00000000";
        if (CICSACR.DATA.STSW == null) CICSACR.DATA.STSW = "";
        JIBS_tmp_int = CICSACR.DATA.STSW.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) CICSACR.DATA.STSW += " ";
        CICSACR.DATA.STSW = "1" + CICSACR.DATA.STSW.substring(1);
        if (CICSACR.DATA.STSW == null) CICSACR.DATA.STSW = "";
        JIBS_tmp_int = CICSACR.DATA.STSW.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) CICSACR.DATA.STSW += " ";
        CICSACR.DATA.STSW = CICSACR.DATA.STSW.substring(0, 4 - 1) + "1" + CICSACR.DATA.STSW.substring(4 + 1 - 1);
        CICSACR.DATA.PROD_CD = WS_PROD_CD;
        CICSACR.DATA.FRM_APP = "DD";
        CICSACR.DATA.CCY = FSCFOPVS.CCY;
        CICSACR.DATA.SHOW_FLG = 'N';
        CICSACR.DATA.AC_CNM = FSCFOPVS.ACC_NAME;
        CICSACR.DATA.OPN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CICSACR.DATA.OPEN_DT = SCCGWA.COMM_AREA.AC_DATE;
        S000_CALL_CIZSACR();
        IBS.init(SCCGWA, CICSACRL);
        CICSACRL.FUNC = 'A';
        CICSACRL.DATA.AC_NO = FSCFOPVS.ACC_NO;
        CICSACRL.DATA.AC_REL = "01";
        CICSACRL.DATA.REL_AC_NO = FSCFOPVS.UPACC_NO;
        CICSACRL.DATA.DEFAULT = '1';
        S000_CALL_CIZSACRL();
    }
    public void B040_UPDATE_FSTVMST_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FSRVMST);
        FSRVMST.KEY.ACC_NO = FSCFOPVS.ACC_NO;
        T000_READ_UPDATE_FSTVMST();
        if (!FSCFOPVS.ACC_NAME.equalsIgnoreCase(FSRVMST.ACC_NAME)) {
            IBS.init(SCCGWA, CICSACR);
            CICSACR.FUNC = 'M';
            CICSACR.DATA.AGR_NO = FSCFOPVS.ACC_NO;
            CICSACR.DATA.AC_CNM = FSCFOPVS.ACC_NAME;
            S000_CALL_CIZSACR();
        }
        FSRVMST.KEY.ACC_NO = FSCFOPVS.ACC_NO;
        FSRVMST.FLOW_NO = FSCFOPVS.FLOW_NO;
        FSRVMST.GROUP_NO = FSCFOPVS.GROUP_NO;
        FSRVMST.CUST_NO = WS_CI_NO;
        FSRVMST.ACC_NAME = FSCFOPVS.ACC_NAME;
        FSRVMST.UPCUST_NO = WS_CI_NO;
        FSRVMST.UPACC_NO = FSCFOPVS.UPACC_NO;
        FSRVMST.CCY = FSCFOPVS.CCY;
        FSRVMST.CCY_TYP = FSCFOPVS.CCY_TYP;
        FSRVMST.VIR_FLAG = FSCFOPVS.VIR_FLAG;
        FSRVMST.UPACC_VIR_FLAG = FSCFOPVS.UPACC_VIR_FLAG;
        FSRVMST.STS_WORD = "00000000000000000000000000000000";
        FSRVMST.REMARK = FSCFOPVS.REMARK;
        FSRVMST.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        FSRVMST.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        FSRVMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        FSRVMST.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        FSRVMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, FSRVMST);
        T000_REWRITE_FSTVMST();
    }
    public void B045_CLOSE_VSAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FSCFCLVS);
        FSCFCLVS.ACC_NO = FSCFOPVS.ACC_NO;
        FSCFCLVS.CCY = FSCFOPVS.CCY;
        FSCFCLVS.CCY_TYP = FSCFOPVS.CCY_TYP;
        FSCFCLVS.FLOW_NO = FSCFOPVS.FLOW_NO;
        FSCFCLVS.REMARK = FSCFOPVS.REMARK;
        S000_CALL_FSZFCLVS();
    }
    public void B050_BP_NFHIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.AC = FSCFOPVS.ACC_NO;
        BPCPNHIS.INFO.TX_TOOL = FSCFOPVS.ACC_NO;
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.CI_NO = " ";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.REF_NO = " ";
        BPCPNHIS.INFO.TX_RMK = FSCFOPVS.REMARK;
        BPCPNHIS.INFO.NEW_DAT_PT = FSCFOPVS;
        BPCPNHIS.INFO.FMT_ID = "FSZFOPVS";
        BPCPNHIS.INFO.FMT_ID_LEN = 749;
        S000_CALL_BPZPNHIS();
    }
    public void B055_WRT_FSTVMST_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FSRVMST);
        FSRVMST.KEY.ACC_NO = FSCFOPVS.ACC_NO;
        FSRVMST.FLOW_NO = FSCFOPVS.FLOW_NO;
        FSRVMST.GROUP_NO = FSCFOPVS.GROUP_NO;
        FSRVMST.CUST_NO = WS_CI_NO;
        FSRVMST.ACC_NAME = FSCFOPVS.ACC_NAME;
        FSRVMST.UPCUST_NO = WS_CI_NO;
        FSRVMST.UPACC_NO = FSCFOPVS.UPACC_NO;
        FSRVMST.CCY = FSCFOPVS.CCY;
        FSRVMST.CCY_TYP = FSCFOPVS.CCY_TYP;
        FSRVMST.VIR_FLAG = FSCFOPVS.VIR_FLAG;
        FSRVMST.UPACC_VIR_FLAG = FSCFOPVS.UPACC_VIR_FLAG;
        FSRVMST.STS_WORD = "00000000000000000000000000000000";
        FSRVMST.REMARK = FSCFOPVS.REMARK;
        FSRVMST.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        FSRVMST.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        FSRVMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        FSRVMST.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        FSRVMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_FSTVMST();
    }
    public void B020_COUNT_FSTVMST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FSRVMST);
        FSRVMST.UPACC_NO = FSCFOPVS.UPACC_NO;
        CEP.TRC(SCCGWA, FSRVMST.UPACC_NO);
        FSTVMST_RD = new DBParm();
        FSTVMST_RD.TableName = "FSTVMST";
        FSTVMST_RD.set = "WS-COUNT=COUNT(*)";
        FSTVMST_RD.where = "UPACC_NO = :FSRVMST.UPACC_NO";
        IBS.GROUP(SCCGWA, FSRVMST, this, FSTVMST_RD);
        CEP.TRC(SCCGWA, FSRVMST.UPACC_NO);
        CEP.TRC(SCCGWA, WS_COUNT);
    }
    public void B060_SET_STSW_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = FSCFOPVS.UPACC_NO;
        S000_CALL_CIZQACAC();
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        IBS.init(SCCGWA, DDCIMCYY);
        DDCIMCYY.TX_TYPE = 'S';
        DDCIMCYY.DATA.SET_FLG = 'O';
        DDCIMCYY.DATA.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDCIMCYY.DATA.STS_CD = "64";
        S000_CALL_DDZIMCYY();
    }
    public void B065_CAL_STSW_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = FSCFOPVS.UPACC_NO;
        S000_CALL_CIZQACAC();
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        IBS.init(SCCGWA, DDCIMCYY);
        DDCIMCYY.TX_TYPE = 'S';
        DDCIMCYY.DATA.SET_FLG = 'F';
        DDCIMCYY.DATA.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDCIMCYY.DATA.STS_CD = "64";
        S000_CALL_DDZIMCYY();
    }
    public void B070_SET_RES() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FSCOOPVS);
        FSCOOPVS.FLOW_NO = FSCFOPVS.FLOW_NO;
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = FSCOOPVS;
        SCCFMT.DATA_LEN = 14;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_UPDATE_FSTVMST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, FSRVMST.KEY.ACC_NO);
        FSTVMST_RD = new DBParm();
        FSTVMST_RD.TableName = "FSTVMST";
        FSTVMST_RD.upd = true;
        IBS.READ(SCCGWA, FSRVMST, FSTVMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_LPAY_REC_NOTFND;
            S000_ERR_MSG_PROC();
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "READ TABLE FSTVMST ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "FSTVMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_REWRITE_FSTVMST() throws IOException,SQLException,Exception {
        FSTVMST_RD = new DBParm();
        FSTVMST_RD.TableName = "FSTVMST";
        IBS.REWRITE(SCCGWA, FSRVMST, FSTVMST_RD);
    }
    public void T000_WRITE_FSTVMST() throws IOException,SQLException,Exception {
        FSRVMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        FSRVMST.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        FSRVMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        FSTVMST_RD = new DBParm();
        FSTVMST_RD.TableName = "FSTVMST";
        FSTVMST_RD.errhdl = true;
        IBS.WRITE(SCCGWA, FSRVMST, FSTVMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_MST1_REC_EXIST;
            S000_ERR_MSG_PROC();
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "WRITE TABLE FSTVMST ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "FSTVMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
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
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CCI_INQ_ACCU, CICACCU);
        if (CICACCU.RC.RC_CODE == 0) {
        } else {
            WS_MSG_ID = CICACCU.RC.RC_MMO;
            S000_ERR_MSG_PROC();
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
    public void S000_CALL_CIZSACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACRL", CICSACRL);
        if (CICSACRL.RC.RC_CODE == 0) {
        } else {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, CICSACRL.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZSACR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACR", CICSACR);
        if (CICSACR.RC.RC_CODE == 0) {
        } else {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, CICSACR.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DDZUOPAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-OPEN-AC     ", DDCUOPAC);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CDD_U_BPZPNHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DDZIMCYY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-ZIMCYY-AC", DDCIMCYY);
        CEP.TRC(SCCGWA, DDCIMCYY.RC.RC_CODE);
        if (DDCIMCYY.RC.RC_CODE != 0) {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, DDCIMCYY.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_FSZFCLVS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CFS_C_VIRTUAL_AC, FSCFCLVS);
        if (FSCFCLVS.RC.RC_CODE != 0) {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, FSCFCLVS.RC);
            S000_ERR_MSG_PROC();
        }
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
