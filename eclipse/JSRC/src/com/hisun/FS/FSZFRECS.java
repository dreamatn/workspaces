package com.hisun.FS;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.DD.*;
import com.hisun.CI.*;
import com.hisun.DC.*;

import java.io.IOException;
import java.sql.SQLException;

public class FSZFRECS {
    int JIBS_tmp_int;
    DBParm FSTMST_RD;
    DBParm DDTCCY_RD;
    String CDD_U_BPZPNHIS = "BP-REC-NHIS";
    String K_OUTPUT_FMT = "FS500";
    double K_HIGH_AMT = 9999999999999.99;
    String WS_MSG_ID = " ";
    String WS_ERR_INFO = " ";
    char WS_AC_STS = ' ';
    String WS_AC_STS_WORD = " ";
    int WS_VS_SEQ = 0;
    char WS_AC_TYPE = ' ';
    char WS_TBL_FLAG = ' ';
    SCCCLDT SCCCLDT = new SCCCLDT();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    FSCMSG_ERROR_MSG FSCMSG_ERROR_MSG = new FSCMSG_ERROR_MSG();
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
    FSCORECS FSCORECS = new FSCORECS();
    DDCIMCYY DDCIMCYY = new DDCIMCYY();
    DDCSCPLC DDCSCPLC = new DDCSCPLC();
    DDCIMMST DDCIMMST = new DDCIMMST();
    BPCSOCAC BPCSOCAC = new BPCSOCAC();
    CICCUST CICCUST = new CICCUST();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    DDRCCY DDRCCY = new DDRCCY();
    DCRHLD DCRHLD = new DCRHLD();
    FSRMST FSRMST = new FSRMST();
    int WS_COUNT = 0;
    int WS_OTH_COUNT = 0;
    int WS_LOWER_COUNT = 0;
    SCCGWA SCCGWA;
    BPCPORUP_DATA_INFO BPCPORUP;
    FSCFRECS FSCFRECS;
    public void MP(SCCGWA SCCGWA, FSCFRECS FSCFRECS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.FSCFRECS = FSCFRECS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "FSZFRECS return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INFO();
        if (FSCFRECS.ACTION_TYPE == 'A') {
            B025_WRT_FSTMST_PROC();
            B030_SET_ACSTSW_PROC();
            B020_COUNT_FSTMST();
            B020_LOWER_CHECK();
            if (WS_AC_TYPE != '7' 
                && WS_COUNT == 1 
                && WS_LOWER_COUNT == 0) {
                B045_SET_UPACSTSW_PROC();
            }
        } else if (FSCFRECS.ACTION_TYPE == 'U') {
            B040_UPDATE_FSTMST_PROC();
        } else if (FSCFRECS.ACTION_TYPE == 'D') {
            B020_CHECK_DOWN_REL();
            if (WS_OTH_COUNT != 0) {
                WS_MSG_ID = FSCMSG_ERROR_MSG.FS_POOL_EXT_OTH_REL;
                S000_ERR_MSG_PROC();
            }
            B020_COUNT_FSTMST();
            B050_DELETE_FSTMST_PROC();
            B055_CAL_ACSTSW_PROC();
            if (WS_AC_TYPE != '7' 
                && WS_COUNT == 1) {
                B060_CAL_UPACSTSW_PROC();
            }
        } else {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_FUNC_TYPE_ERR;
            S000_ERR_MSG_PROC();
        }
        B035_BP_NFHIS();
        B070_SET_RES();
    }
    public void B010_CHECK_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = FSCFRECS.ACC_NO;
        CICQACAC.DATA.CCY_ACAC = FSCFRECS.CCY;
        CICQACAC.DATA.CR_FLG = FSCFRECS.CCY_TYP;
        CEP.TRC(SCCGWA, FSCFRECS.ACC_NO);
        S000_CALL_CIZQACAC();
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        IBS.init(SCCGWA, DDCIMMST);
        DDCIMMST.DATA.KEY.AC_NO = FSCFRECS.ACC_NO;
        DDCIMMST.TX_TYPE = 'I';
        S000_CALL_DDZIMMST();
        CEP.TRC(SCCGWA, DDCIMMST.DATA.AC_STS);
        DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        T000_READ_DDTCCY();
        if (WS_TBL_FLAG == 'N') {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
        WS_AC_STS = DDCIMMST.DATA.AC_STS;
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
        if (DDRCCY.AC_TYPE != '1') {
            WS_MSG_ID = FSCMSG_ERROR_MSG.FS_CANOT_REG;
            S000_ERR_MSG_PROC();
        }
        if (WS_AC_STS == 'C') {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
            S000_ERR_MSG_PROC();
        }
        if (FSCFRECS.ACTION_TYPE == 'A' 
            || FSCFRECS.ACTION_TYPE == 'U') {
            if (WS_AC_STS == 'D') {
                WS_MSG_ID = DDCMSG_ERROR_MSG.DD_AC_L_HANGED;
                S000_ERR_MSG_PROC();
            }
            if (WS_AC_STS == 'M') {
                WS_MSG_ID = DDCMSG_ERROR_MSG.DD_AC_HAND;
                S000_ERR_MSG_PROC();
            }
            if (FSCFRECS.CTRL_FLG == '1' 
                && FSCFRECS.LIMIT_AMT <= 0) {
                WS_MSG_ID = FSCMSG_ERROR_MSG.FS_MUST_LG_ZERO;
                S000_ERR_MSG_PROC();
            }
        }
        if (FSCFRECS.ACTION_TYPE == 'A') {
            if (WS_AC_STS == 'V') {
                WS_MSG_ID = DDCMSG_ERROR_MSG.DD_INACTIVE_AC;
                S000_ERR_MSG_PROC();
            }
            if (WS_AC_STS == 'O') {
                WS_MSG_ID = DDCMSG_ERROR_MSG.DD_INACTIVE_AC;
                S000_ERR_MSG_PROC();
            }
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.FUNC = 'R';
            CICQACAC.DATA.AGR_NO = FSCFRECS.UPACC_NO;
            CICQACAC.DATA.CCY_ACAC = FSCFRECS.CCY;
            CICQACAC.DATA.CR_FLG = FSCFRECS.CCY_TYP;
            S000_CALL_CIZQACAC();
            CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
            IBS.init(SCCGWA, DDCIMMST);
            DDCIMMST.DATA.KEY.AC_NO = FSCFRECS.UPACC_NO;
            DDCIMMST.TX_TYPE = 'I';
            S000_CALL_DDZIMMST();
            CEP.TRC(SCCGWA, DDCIMMST.DATA.AC_STS);
            IBS.init(SCCGWA, DDRCCY);
            DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            T000_READ_DDTCCY();
            CEP.TRC(SCCGWA, DDRCCY.CCY);
            CEP.TRC(SCCGWA, DDRCCY.CCY_TYPE);
            WS_AC_TYPE = DDRCCY.AC_TYPE;
            if (!FSCFRECS.CCY.equalsIgnoreCase(DDRCCY.CCY) 
                || FSCFRECS.CCY_TYP != DDRCCY.CCY_TYPE) {
                WS_MSG_ID = DDCMSG_ERROR_MSG.DD_CCY_NO_SAME;
                S000_ERR_MSG_PROC();
            }
            if (WS_TBL_FLAG == 'N') {
                WS_MSG_ID = DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND;
                S000_ERR_MSG_PROC();
            }
            WS_AC_STS = DDCIMMST.DATA.AC_STS;
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
            if (WS_AC_STS == 'D') {
                WS_MSG_ID = DDCMSG_ERROR_MSG.DD_AC_L_HANGED;
                S000_ERR_MSG_PROC();
            }
            if (WS_AC_STS == 'M') {
                WS_MSG_ID = DDCMSG_ERROR_MSG.DD_AC_HAND;
                S000_ERR_MSG_PROC();
            }
            if (WS_AC_STS == 'V') {
                WS_MSG_ID = DDCMSG_ERROR_MSG.DD_INACTIVE_AC;
                S000_ERR_MSG_PROC();
            }
            if (WS_AC_STS == 'O') {
                WS_MSG_ID = DDCMSG_ERROR_MSG.DD_INACTIVE_AC;
                S000_ERR_MSG_PROC();
            }
        }
        if (FSCFRECS.ACTION_TYPE == 'D') {
            if (FSRMST.SS_BAL > 0 
                || FSRMST.XH_BAL > 0) {
            }
        }
    }
    public void B020_CHECK_DOWN_REL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FSRMST);
        FSRMST.UPACC_NO = FSCFRECS.ACC_NO;
        CEP.TRC(SCCGWA, FSRMST.UPACC_NO);
        FSTMST_RD = new DBParm();
        FSTMST_RD.TableName = "FSTMST";
        FSTMST_RD.set = "WS-OTH-COUNT=COUNT(*)";
        FSTMST_RD.where = "UPACC_NO = :FSRMST.UPACC_NO";
        IBS.GROUP(SCCGWA, FSRMST, this, FSTMST_RD);
        CEP.TRC(SCCGWA, FSRMST.UPACC_NO);
        CEP.TRC(SCCGWA, WS_OTH_COUNT);
    }
    public void B020_COUNT_FSTMST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FSRMST);
        FSRMST.UPACC_NO = FSCFRECS.UPACC_NO;
        CEP.TRC(SCCGWA, FSRMST.UPACC_NO);
        FSTMST_RD = new DBParm();
        FSTMST_RD.TableName = "FSTMST";
        FSTMST_RD.set = "WS-COUNT=COUNT(*)";
        FSTMST_RD.where = "UPACC_NO = :FSRMST.UPACC_NO";
        IBS.GROUP(SCCGWA, FSRMST, this, FSTMST_RD);
        CEP.TRC(SCCGWA, FSRMST.UPACC_NO);
        CEP.TRC(SCCGWA, WS_COUNT);
    }
    public void B020_LOWER_CHECK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FSRMST);
        FSRMST.KEY.ACC_NO = FSCFRECS.UPACC_NO;
        CEP.TRC(SCCGWA, FSCFRECS.UPACC_NO);
        FSTMST_RD = new DBParm();
        FSTMST_RD.TableName = "FSTMST";
        FSTMST_RD.set = "WS-LOWER-COUNT=COUNT(*)";
        FSTMST_RD.where = "ACC_NO = :FSRMST.KEY.ACC_NO";
        IBS.GROUP(SCCGWA, FSRMST, this, FSTMST_RD);
        CEP.TRC(SCCGWA, FSRMST.KEY.ACC_NO);
        CEP.TRC(SCCGWA, WS_LOWER_COUNT);
    }
    public void B025_WRT_FSTMST_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FSRMST);
        FSRMST.KEY.ACC_NO = FSCFRECS.ACC_NO;
        FSRMST.FLOW_NO = FSCFRECS.FLOW_NO;
        FSRMST.GROUP_NO = FSCFRECS.GROUP_NO;
        FSRMST.CUST_NO = FSCFRECS.CUST_NO;
        FSRMST.ACC_NAME = FSCFRECS.ACC_NAME;
        FSRMST.UPCUST_NO = FSCFRECS.UPCUST_NO;
        FSRMST.UPACC_NO = FSCFRECS.UPACC_NO;
        FSRMST.CCY = FSCFRECS.CCY;
        FSRMST.CCY_TYP = FSCFRECS.CCY_TYP;
        FSRMST.RT_FLAG = FSCFRECS.RT_FLAG;
        FSRMST.KEEP_AMT = FSCFRECS.KEEP_AMT;
        FSRMST.PAY_FLAG = FSCFRECS.PAY_FLAG;
        if (FSCFRECS.CTRL_FLG == '0') {
            FSRMST.LIMIT_AMT = K_HIGH_AMT;
        } else if (FSCFRECS.CTRL_FLG == '1') {
            FSRMST.LIMIT_AMT = FSCFRECS.LIMIT_AMT;
        } else if (FSCFRECS.CTRL_FLG == '2') {
            FSRMST.LIMIT_AMT = 0;
        }
        FSRMST.FT_FLAG = FSCFRECS.FT_FLAG;
        FSRMST.STS_WORD = "00000000000000000000000000000000";
        FSRMST.FLOW_TYP = FSCFRECS.BUS_TYP;
        FSRMST.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        FSRMST.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        FSRMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        FSRMST.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        FSRMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, K_HIGH_AMT);
        CEP.TRC(SCCGWA, FSRMST.LIMIT_AMT);
        T000_WRITE_FSTMST();
    }
    public void B030_SET_ACSTSW_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = FSCFRECS.ACC_NO;
        CICQACAC.DATA.CCY_ACAC = FSCFRECS.CCY;
        CICQACAC.DATA.CR_FLG = FSCFRECS.CCY_TYP;
        S000_CALL_CIZQACAC();
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        IBS.init(SCCGWA, DDCIMCYY);
        DDCIMCYY.TX_TYPE = 'S';
        DDCIMCYY.DATA.SET_FLG = 'O';
        DDCIMCYY.DATA.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDCIMCYY.DATA.STS_CD = "63";
        S000_CALL_DDZIMCYY();
    }
    public void B035_BP_NFHIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.AC = FSCFRECS.ACC_NO;
        BPCPNHIS.INFO.TX_TOOL = FSCFRECS.ACC_NO;
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.CI_NO = FSCFRECS.CUST_NO;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.REF_NO = " ";
        BPCPNHIS.INFO.TX_RMK = " ";
        BPCPNHIS.INFO.NEW_DAT_PT = FSCFRECS;
        BPCPNHIS.INFO.FMT_ID = "FSZFRECS";
        BPCPNHIS.INFO.FMT_ID_LEN = 408;
        S000_CALL_BPZPNHIS();
    }
    public void B040_UPDATE_FSTMST_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FSRMST);
        FSRMST.KEY.ACC_NO = FSCFRECS.ACC_NO;
        T000_READ_UPDATE_FSTMST();
        FSRMST.KEY.ACC_NO = FSCFRECS.ACC_NO;
        FSRMST.FLOW_NO = FSCFRECS.FLOW_NO;
        FSRMST.GROUP_NO = FSCFRECS.GROUP_NO;
        FSRMST.CUST_NO = FSCFRECS.CUST_NO;
        FSRMST.ACC_NAME = FSCFRECS.ACC_NAME;
        FSRMST.UPCUST_NO = FSCFRECS.UPCUST_NO;
        FSRMST.UPACC_NO = FSCFRECS.UPACC_NO;
        FSRMST.CCY = FSCFRECS.CCY;
        FSRMST.CCY_TYP = FSCFRECS.CCY_TYP;
        FSRMST.RT_FLAG = FSCFRECS.RT_FLAG;
        FSRMST.KEEP_AMT = FSCFRECS.KEEP_AMT;
        FSRMST.PAY_FLAG = FSCFRECS.PAY_FLAG;
        if (FSCFRECS.CTRL_FLG == '0') {
            FSRMST.LIMIT_AMT = K_HIGH_AMT;
        } else if (FSCFRECS.CTRL_FLG == '1') {
            FSRMST.LIMIT_AMT = FSCFRECS.LIMIT_AMT;
        } else if (FSCFRECS.CTRL_FLG == '2') {
            FSRMST.LIMIT_AMT = 0;
        }
        FSRMST.FT_FLAG = FSCFRECS.FT_FLAG;
        FSRMST.STS_WORD = "00000000000000000000000000000000";
        FSRMST.FLOW_TYP = FSCFRECS.BUS_TYP;
        FSRMST.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        FSRMST.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        FSRMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        FSRMST.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        FSRMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, FSRMST);
        T000_REWRITE_FSTMST();
    }
    public void B045_SET_UPACSTSW_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = FSCFRECS.UPACC_NO;
        CICQACAC.DATA.CCY_ACAC = FSCFRECS.CCY;
        CICQACAC.DATA.CR_FLG = FSCFRECS.CCY_TYP;
        S000_CALL_CIZQACAC();
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        IBS.init(SCCGWA, DDCIMCYY);
        DDCIMCYY.TX_TYPE = 'S';
        DDCIMCYY.DATA.SET_FLG = 'O';
        DDCIMCYY.DATA.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDCIMCYY.DATA.STS_CD = "62";
        S000_CALL_DDZIMCYY();
    }
    public void B050_DELETE_FSTMST_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FSRMST);
        FSRMST.KEY.ACC_NO = FSCFRECS.ACC_NO;
        T000_READ_UPDATE_FSTMST();
        T000_DELETE_FSTMST();
    }
    public void B055_CAL_ACSTSW_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = FSCFRECS.ACC_NO;
        CICQACAC.DATA.CCY_ACAC = FSCFRECS.CCY;
        CICQACAC.DATA.CR_FLG = FSCFRECS.CCY_TYP;
        S000_CALL_CIZQACAC();
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        IBS.init(SCCGWA, DDCIMCYY);
        DDCIMCYY.TX_TYPE = 'S';
        DDCIMCYY.DATA.SET_FLG = 'F';
        DDCIMCYY.DATA.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDCIMCYY.DATA.STS_CD = "63";
        S000_CALL_DDZIMCYY();
    }
    public void B060_CAL_UPACSTSW_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = FSCFRECS.UPACC_NO;
        CICQACAC.DATA.CCY_ACAC = FSCFRECS.CCY;
        CICQACAC.DATA.CR_FLG = FSCFRECS.CCY_TYP;
        S000_CALL_CIZQACAC();
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        IBS.init(SCCGWA, DDCIMCYY);
        DDCIMCYY.TX_TYPE = 'S';
        DDCIMCYY.DATA.SET_FLG = 'F';
        DDCIMCYY.DATA.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDCIMCYY.DATA.STS_CD = "62";
        S000_CALL_DDZIMCYY();
    }
    public void B070_SET_RES() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FSCORECS);
        FSCORECS.FLOW_NO = FSCFRECS.FLOW_NO;
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = FSCORECS;
        SCCFMT.DATA_LEN = 14;
        IBS.FMT(SCCGWA, SCCFMT);
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
        FSTMST_RD = new DBParm();
        FSTMST_RD.TableName = "FSTMST";
        IBS.REWRITE(SCCGWA, FSRMST, FSTMST_RD);
    }
    public void T000_WRITE_FSTMST() throws IOException,SQLException,Exception {
        FSTMST_RD = new DBParm();
        FSTMST_RD.TableName = "FSTMST";
        FSTMST_RD.errhdl = true;
        IBS.WRITE(SCCGWA, FSRMST, FSTMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_MST1_REC_EXIST;
            S000_ERR_MSG_PROC();
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "WRITE TABLE FSTMST ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "FSTMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_DELETE_FSTMST() throws IOException,SQLException,Exception {
        FSTMST_RD = new DBParm();
        FSTMST_RD.TableName = "FSTMST";
        IBS.DELETE(SCCGWA, FSRMST, FSTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "DYQ DELETE FSTMST SUCC");
        } else {
            CEP.TRC(SCCGWA, "DYQ DELETE FSTMST FAILED");
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "DELETE TABLE FSTMST ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "FSTMST";
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
    public void S000_CALL_DDZIMMST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-NFIN-M-MST", DDCIMMST);
        if (DDCIMMST.RC.RC_CODE != 0) {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, DDCIMMST.RC);
            S000_ERR_MSG_PROC();
        }
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
