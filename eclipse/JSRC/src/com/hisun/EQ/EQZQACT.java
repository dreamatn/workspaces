package com.hisun.EQ;

import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class EQZQACT {
    int JIBS_tmp_int;
    DBParm EQTACT_RD;
    DBParm EQTACTD_RD;
    DBParm EQTBVT_RD;
    brParm EQTACT_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int K_MAX_ROW = 12;
    int K_MAX_COL = 500;
    String K_OUTPUT_FMT_9 = "EQ105";
    String K_BSZ_BANKID = "01";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_ACT_FLG = ' ';
    char WS_BVT_FLG = ' ';
    char WS_OUTPUT_FLG = ' ';
    String WS_EQ_TYPE = " ";
    char WS_AC_STS = ' ';
    String WS_PSBK_NO = " ";
    char WS_NORMAL_STS = 'N';
    EQCMSG_ERROR_MSG EQCMSG_ERROR_MSG = new EQCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    EQRACTD EQRACTD = new EQRACTD();
    EQRACT EQRACT = new EQRACT();
    EQRBVT EQRBVT = new EQRBVT();
    CICCUST CICCUST = new CICCUST();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    EQCOQ100_OPT_8100 EQCOQ100_OPT_8100 = new EQCOQ100_OPT_8100();
    EQCOQ105_OPT_8105 EQCOQ105_OPT_8105 = new EQCOQ105_OPT_8105();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    EQCQACT EQCQACT;
    EQCSACT EQCSACT;
    public void MP(SCCGWA SCCGWA, EQCQACT EQCQACT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.EQCQACT = EQCQACT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "EQZQACT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        EQCQACT.RC.RC_MMO = "EQ";
        EQCQACT.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (EQCQACT.DATA.EQ_BKID.trim().length() == 0) {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_BANKID_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_EQ_TYPE = "" + EQCQACT.DATA.EQ_TYP;
        JIBS_tmp_int = WS_EQ_TYPE.length();
        for (int i=0;i<1-JIBS_tmp_int;i++) WS_EQ_TYPE = "0" + WS_EQ_TYPE;
        if ((!WS_EQ_TYPE.equalsIgnoreCase("1") 
            && !WS_EQ_TYPE.equalsIgnoreCase("2") 
            && !WS_EQ_TYPE.equalsIgnoreCase("3") 
            && !WS_EQ_TYPE.equalsIgnoreCase("4"))) {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_INVALID_TYP;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_AC_STS = EQCQACT.DATA.AC_STS;
        if ((WS_AC_STS != 'N' 
            && WS_AC_STS != 'C' 
            && WS_AC_STS != 'R')) {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_INVALID_AC_STS;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B200_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, EQCQACT.FUNC);
        if (EQCQACT.FUNC == 'I') {
            B210_INQUIRE_PROC();
            if (pgmRtn) return;
        } else if (EQCQACT.FUNC == 'B') {
            B220_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (EQCQACT.FUNC == 'F') {
            B230_BROWSE_FIRST_PROC();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_INVALID_FUNC;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B210_INQUIRE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQRACT);
        WS_ACT_FLG = ' ';
        EQRACT.KEY.EQ_BKID = EQCQACT.DATA.EQ_BKID;
        EQRACT.KEY.EQ_AC = EQCQACT.DATA.EQ_AC;
        T000_READ_EQTACT_BY_EQAC();
        if (pgmRtn) return;
        if (WS_ACT_FLG == 'Y') {
            B210_01_DATA_TRANS_TO_FMT();
            if (pgmRtn) return;
            B210_02_DATA_OUTPUT_FMT();
            if (pgmRtn) return;
        } else {
            CEP.ERR(SCCGWA, EQCMSG_ERROR_MSG.EQ_EQAC_NOTFND);
        }
    }
    public void B220_BROWSE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQRACT);
        WS_ACT_FLG = ' ';
        EQRACT.KEY.EQ_BKID = EQCQACT.DATA.EQ_BKID;
        EQRACT.KEY.EQ_AC = EQCQACT.DATA.EQ_AC;
        EQRACT.CI_NO = EQCQACT.DATA.CI_NO;
        EQRACT.EQ_ACT = EQCQACT.DATA.EQ_ACT;
        EQRACT.EQ_CINO = EQCQACT.DATA.EQ_CINO;
        EQRACT.AC_STS = EQCQACT.DATA.AC_STS;
        EQRACT.EQ_TYP = EQCQACT.DATA.EQ_TYP;
        T000_STARTBR_EQTACT();
        if (pgmRtn) return;
        T000_READNEXT_EQTACT();
        if (pgmRtn) return;
        if (WS_ACT_FLG == 'Y') {
            B220_01_OUT_TITLE();
            if (pgmRtn) return;
        }
        while (WS_ACT_FLG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            B220_02_OUT_BRW_DATA();
            if (pgmRtn) return;
            T000_READNEXT_EQTACT();
            if (pgmRtn) return;
        }
        T000_ENDBR_EQTACT();
        if (pgmRtn) return;
    }
    public void B230_BROWSE_FIRST_PROC() throws IOException,SQLException,Exception {
        if (EQCQACT.DATA.DIV_AC.trim().length() == 0) {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_DIV_AC_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, EQRACT);
        WS_ACT_FLG = ' ';
        EQRACT.DIV_AC = EQCQACT.DATA.DIV_AC;
        EQRACT.AC_STS = 'N';
        T000_READ_EQTACT_DIVAC();
        if (pgmRtn) return;
        if (WS_ACT_FLG == 'N') {
            EQCQACT.DATA.FOUND_FLG = 'Y';
        } else {
            EQCQACT.DATA.FOUND_FLG = ' ';
        }
    }
    public void B210_01_DATA_TRANS_TO_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQCOQ105_OPT_8105);
        IBS.init(SCCGWA, BPCPQPRD);
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        if (BPCPQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_PROD_CD_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            EQCOQ105_OPT_8105.PROD_NM = BPCPQPRD.PRDT_NAME;
        }
        IBS.init(SCCGWA, CICCUST);
        CEP.TRC(SCCGWA, EQRACT.CI_NO);
        CICCUST.DATA.CI_NO = EQRACT.CI_NO;
        CICCUST.FUNC = 'C';
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        if (CICCUST.RC.RC_CODE == 0) {
            EQCOQ105_OPT_8105.EQ_CNM = CICCUST.O_DATA.O_CI_NM;
            EQCOQ105_OPT_8105.ID_TYP = CICCUST.O_DATA.O_ID_TYPE;
            EQCOQ105_OPT_8105.ID_NO = CICCUST.O_DATA.O_ID_NO;
        } else {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_CINO_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        T000_READ_BV_FIRST();
        if (pgmRtn) return;
        EQCOQ105_OPT_8105.EQ_BKID = EQRACT.KEY.EQ_BKID;
        EQCOQ105_OPT_8105.PROD_CD = EQRACT.PROD_CD;
        EQCOQ105_OPT_8105.EQ_AC = EQRACT.KEY.EQ_AC;
        EQCOQ105_OPT_8105.EQ_ACT = EQRACT.EQ_ACT;
        EQCOQ105_OPT_8105.EQ_CINO = EQRACT.EQ_CINO;
        EQCOQ105_OPT_8105.PSBK_NO = WS_PSBK_NO;
        EQCOQ105_OPT_8105.CI_NO = EQRACT.CI_NO;
        EQCOQ105_OPT_8105.TEL_NO = EQRACT.TEL_NO;
        EQCOQ105_OPT_8105.EQ_ADDR = EQRACT.EQ_ADDR;
        EQCOQ105_OPT_8105.EQ_TYP = EQRACT.EQ_TYP;
        EQCOQ105_OPT_8105.CCY = EQRACT.CCY;
        EQCOQ105_OPT_8105.EQ_QTY = EQRACT.EQ_QTY;
        EQCOQ105_OPT_8105.FRZ_QTY = EQRACT.FRZ_QTY;
        EQCOQ105_OPT_8105.PLG_QTY = EQRACT.PLG_QTY;
        EQCOQ105_OPT_8105.LS_DVDT = EQRACT.LS_DIVIDEND_DT;
        EQCOQ105_OPT_8105.LS_DPDT = EQRACT.LS_DIV_PROC_DT;
        EQCOQ105_OPT_8105.LS_DVAMT = EQRACT.LS_DIV_AMT;
        EQCOQ105_OPT_8105.LS_CPNDT = EQRACT.LS_COUPON_DT;
        EQCOQ105_OPT_8105.LS_CPPDT = EQRACT.LS_CPN_PROC_DT;
        EQCOQ105_OPT_8105.LS_CPQTY = EQRACT.LS_CPN_QTY;
        EQCOQ105_OPT_8105.ADD_BR = EQRACT.ADD_BR;
        EQCOQ105_OPT_8105.ADD_DT = EQRACT.ADD_DT;
        EQCOQ105_OPT_8105.WDAL_BR = EQRACT.WDAL_BR;
        EQCOQ105_OPT_8105.WDAL_DT = EQRACT.WDAL_DT;
        EQCOQ105_OPT_8105.LSTX_DT = EQRACT.LSTX_DT;
        EQCOQ105_OPT_8105.LSUP_DT = EQRACT.UPDTBL_DATE;
        EQCOQ105_OPT_8105.LSUP_TLR = EQRACT.UPDTBL_TLR;
        EQCOQ105_OPT_8105.AC_STS = EQRACT.AC_STS;
        EQCOQ105_OPT_8105.DIV_AC = EQRACT.DIV_AC;
        if (EQRACT.AC_STS == 'C') {
            T000_READ_EQTACTD();
            if (pgmRtn) return;
            EQCOQ105_OPT_8105.WDAL_QTY = EQRACTD.TXN_QTY;
        } else if (EQRACT.AC_STS == 'N') {
            EQCOQ105_OPT_8105.WDAL_QTY = 0;
        } else if (EQRACT.AC_STS == 'R') {
            EQCOQ105_OPT_8105.WDAL_QTY = 0;
        } else {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_INVALID_AC_STS;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B210_02_DATA_OUTPUT_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_9;
        SCCFMT.DATA_PTR = EQCOQ105_OPT_8105;
        SCCFMT.DATA_LEN = 0;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B220_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.MAX_COL_NO = (short) K_MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B220_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQCOQ100_OPT_8100);
        IBS.init(SCCGWA, CICCUST);
        CICCUST.DATA.CI_NO = EQRACT.CI_NO;
        CICCUST.FUNC = 'C';
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        if (CICCUST.RC.RC_CODE == 0) {
            EQCOQ100_OPT_8100.EQ_CNM = CICCUST.O_DATA.O_CI_NM;
            EQCOQ100_OPT_8100.ID_TYP = CICCUST.O_DATA.O_ID_TYPE;
            EQCOQ100_OPT_8100.ID_NO = CICCUST.O_DATA.O_ID_NO;
        } else {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_CINO_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        T000_READ_BV_FIRST();
        if (pgmRtn) return;
        EQCOQ100_OPT_8100.EQ_BKID = EQRACT.KEY.EQ_BKID;
        EQCOQ100_OPT_8100.CI_NO = EQRACT.CI_NO;
        EQCOQ100_OPT_8100.EQ_AC = EQRACT.KEY.EQ_AC;
        EQCOQ100_OPT_8100.EQ_ACT = EQRACT.EQ_ACT;
        EQCOQ100_OPT_8100.EQ_CINO = EQRACT.EQ_CINO;
        EQCOQ100_OPT_8100.PSBK_NO = WS_PSBK_NO;
        EQCOQ100_OPT_8100.EQ_QTY = EQRACT.EQ_QTY;
        EQCOQ100_OPT_8100.EQ_ADDR = EQRACT.EQ_ADDR;
        EQCOQ100_OPT_8100.PROD_CD = EQRACT.PROD_CD;
        EQCOQ100_OPT_8100.CCY = EQRACT.CCY;
        EQCOQ100_OPT_8100.EQ_QTY = EQRACT.EQ_QTY;
        EQCOQ100_OPT_8100.FRZ_QTY = EQRACT.FRZ_QTY;
        EQCOQ100_OPT_8100.PLG_QTY = EQRACT.PLG_QTY;
        EQCOQ100_OPT_8100.AC_STS = EQRACT.AC_STS;
        EQCOQ100_OPT_8100.AC_STSWD = EQRACT.AC_STS_WORD;
        EQCOQ100_OPT_8100.DIV_AC = EQRACT.DIV_AC;
        EQCOQ100_OPT_8100.ADD_BR = EQRACT.ADD_BR;
        EQCOQ100_OPT_8100.ADD_TLR = EQRACT.ADD_TLR;
        EQCOQ100_OPT_8100.ADD_DT = EQRACT.ADD_DT;
        EQCOQ100_OPT_8100.ADD_BR = EQRACT.ADD_BR;
        EQCOQ100_OPT_8100.WDAL_TLR = EQRACT.WDAL_TLR;
        EQCOQ100_OPT_8100.WDAL_DT = EQRACT.WDAL_DT;
        EQCOQ100_OPT_8100.TEL_NO = EQRACT.TEL_NO;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, EQCOQ100_OPT_8100);
        SCCMPAG.DATA_LEN = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void T000_READ_BV_FIRST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQRBVT);
        WS_BVT_FLG = ' ';
        EQRBVT.KEY.EQ_BKID = EQRACT.KEY.EQ_BKID;
        EQRBVT.KEY.EQ_AC = EQRACT.KEY.EQ_AC;
        T000_READ_EQTBVT();
        if (pgmRtn) return;
        if (WS_BVT_FLG == 'Y') {
            WS_PSBK_NO = EQRBVT.KEY.PSBK_NO;
        } else {
            WS_PSBK_NO = " ";
        }
    }
    public void T000_READ_EQTACT_BY_EQAC() throws IOException,SQLException,Exception {
        EQTACT_RD = new DBParm();
        EQTACT_RD.TableName = "EQTACT";
        EQTACT_RD.where = "EQ_BKID = :EQRACT.KEY.EQ_BKID "
            + "AND EQ_AC = :EQRACT.KEY.EQ_AC";
        IBS.READ(SCCGWA, EQRACT, this, EQTACT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ACT_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ACT_FLG = 'N';
        } else {
        }
    }
    public void T000_READ_EQTACT_DIVAC() throws IOException,SQLException,Exception {
        EQTACT_RD = new DBParm();
        EQTACT_RD.TableName = "EQTACT";
        EQTACT_RD.where = "DIV_AC = :EQRACT.DIV_AC "
            + "AND AC_STS = :EQRACT.AC_STS";
        EQTACT_RD.fst = true;
        EQTACT_RD.order = "EQ_BKID,DIV_AC";
        IBS.READ(SCCGWA, EQRACT, this, EQTACT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ACT_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ACT_FLG = 'N';
        } else {
        }
    }
    public void T000_READ_EQTACTD() throws IOException,SQLException,Exception {
        EQTACTD_RD = new DBParm();
        EQTACTD_RD.TableName = "EQTACTD";
        EQTACTD_RD.where = "EQ_BKID = :EQRACT.KEY.EQ_BKID "
            + "AND EQ_AC = :EQRACT.KEY.EQ_AC "
            + "AND TXN_TYP = '3'";
        EQTACTD_RD.fst = true;
        EQTACTD_RD.order = "TXN_DATE,JRN_NO DESC";
        IBS.READ(SCCGWA, EQRACTD, this, EQTACTD_RD);
    }
    public void T000_READ_EQTBVT() throws IOException,SQLException,Exception {
        EQTBVT_RD = new DBParm();
        EQTBVT_RD.TableName = "EQTBVT";
        EQTBVT_RD.where = "EQ_BKID = :EQRBVT.KEY.EQ_BKID "
            + "AND EQ_AC = :EQRBVT.KEY.EQ_AC "
            + "AND PSBK_STS = :WS_NORMAL_STS";
        EQTBVT_RD.fst = true;
        EQTBVT_RD.order = "EQ_BKID,EQ_AC,PSBK_NO";
        IBS.READ(SCCGWA, EQRBVT, this, EQTBVT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_BVT_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_BVT_FLG = 'N';
        } else {
        }
    }
    public void T000_STARTBR_EQTACT() throws IOException,SQLException,Exception {
        EQTACT_BR.rp = new DBParm();
        EQTACT_BR.rp.TableName = "EQTACT";
        EQTACT_BR.rp.where = "( EQ_BKID = :EQRACT.KEY.EQ_BKID "
            + "OR ' ' = :EQRACT.KEY.EQ_BKID ) "
            + "AND ( EQ_AC = :EQRACT.KEY.EQ_AC "
            + "OR ' ' = :EQRACT.KEY.EQ_AC ) "
            + "AND ( CI_NO = :EQRACT.CI_NO "
            + "OR ' ' = :EQRACT.CI_NO ) "
            + "AND ( EQ_ACT = :EQRACT.EQ_ACT "
            + "OR ' ' = :EQRACT.EQ_ACT ) "
            + "AND ( EQ_CINO = :EQRACT.EQ_CINO "
            + "OR ' ' = :EQRACT.EQ_CINO ) "
            + "AND ( EQ_TYP = :EQRACT.EQ_TYP "
            + "OR ' ' = :EQRACT.EQ_TYP ) "
            + "AND ( AC_STS = :EQRACT.AC_STS "
            + "OR ' ' = :EQRACT.AC_STS )";
        EQTACT_BR.rp.order = "EQ_BKID,EQ_AC";
        IBS.STARTBR(SCCGWA, EQRACT, this, EQTACT_BR);
    }
    public void T000_READNEXT_EQTACT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, EQRACT, this, EQTACT_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ACT_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ACT_FLG = 'N';
        } else {
        }
    }
    public void T000_ENDBR_EQTACT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, EQTACT_BR);
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        BPCPQPRD.PRDT_CODE = EQRACT.PROD_CD;
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        CEP.TRC(SCCGWA, BPCPQPRD.RC);
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE);
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
