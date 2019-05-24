package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.CI.*;
import com.hisun.LN.*;
import com.hisun.DD.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1257 {
    BPCBFEED_DATA DATA;
    DBParm BPTFEHIS_RD;
    brParm BPTFEHIS_BR = new brParm();
    String K_OUTPUT_FMT = "BPX01";
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    short WS_FLD_NO = 0;
    int WS_RCD_SEQ = 0;
    int WS_RD_NUM = 0;
    short WS_RECORD_NUM = 0;
    char WS_DB_FLAG = ' ';
    char WS_DB_OPEN_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCBINF SCCBINF = new SCCBINF();
    BPRFEHIS BPRFEHIS = new BPRFEHIS();
    CICCUST CICCUST = new CICCUST();
    LNCICIQ LNCICIQ = new LNCICIQ();
    DDCIMMST DDCIMMST = new DDCIMMST();
    LNRAGRE LNRAGRE = new LNRAGRE();
    LNCRAGRE LNCRAGRE = new LNCRAGRE();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCBFEED BPCBFEED = new BPCBFEED();
    int WS_START_DT = 0;
    int WS_END_DT = 0;
    int WS_COUNT_NO = 0;
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB1250_AWA_1250 BPB1250_AWA_1250;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT1257 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1250_AWA_1250>");
        BPB1250_AWA_1250 = (BPB1250_AWA_1250) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_BRW_FEE_HISTORY();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB1250_AWA_1250.TX_BR);
        CEP.TRC(SCCGWA, BPB1250_AWA_1250.FEE_CODE);
        CEP.TRC(SCCGWA, BPB1250_AWA_1250.PC_FLG);
        CEP.TRC(SCCGWA, BPB1250_AWA_1250.BSNS_NO);
        CEP.TRC(SCCGWA, BPB1250_AWA_1250.PAGE_NUM);
        CEP.TRC(SCCGWA, BPB1250_AWA_1250.PAGE_ROW);
    }
    public void B020_BRW_FEE_HISTORY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCUST);
        IBS.init(SCCGWA, BPRFEHIS);
        BPRFEHIS.CHG_BR = BPB1250_AWA_1250.TX_BR;
        if (BPB1250_AWA_1250.BSNS_NO.trim().length() > 0) {
            BPRFEHIS.BSNS_NO = BPB1250_AWA_1250.BSNS_NO;
        }
        if (BPB1250_AWA_1250.FEE_CODE.trim().length() > 0) {
            BPRFEHIS.FEE_CODE = BPB1250_AWA_1250.FEE_CODE;
        }
        WS_START_DT = BPB1250_AWA_1250.STR_DT;
        WS_END_DT = BPB1250_AWA_1250.END_DT;
        if (BPB1250_AWA_1250.STR_DT == ' ') {
            WS_START_DT = SCCGWA.COMM_AREA.AC_DATE;
        }
        if (BPB1250_AWA_1250.END_DT == ' ') {
            WS_END_DT = SCCGWA.COMM_AREA.AC_DATE;
        }
        CEP.TRC(SCCGWA, BPRFEHIS.BSNS_NO);
        CEP.TRC(SCCGWA, BPRFEHIS.FEE_CODE);
        CEP.TRC(SCCGWA, WS_START_DT);
        CEP.TRC(SCCGWA, WS_END_DT);
        B020_01_OUT_PAGE_TITLE();
        WS_RD_NUM = 1;
        WS_DB_OPEN_FLAG = 'N';
        if (BPCBFEED.TOTAL_DATA.TOTAL_NUM > 0) {
            T000_STARTBR_BPTFEHIS();
            T000_READNEXT_BPTFEHIS_FIRST();
        } else {
            WS_DB_FLAG = 'N';
        }
        while (WS_RD_NUM <= BPB1250_AWA_1250.PAGE_ROW 
            && WS_DB_FLAG != 'N') {
            IBS.init(SCCGWA, BPCBFEED.DATA.set(WS_RD_NUM-1, ));
            CEP.TRC(SCCGWA, BPB1250_AWA_1250.PC_FLG);
            if (BPB1250_AWA_1250.PC_FLG != ' ' 
                && BPRFEHIS.TX_CI.trim().length() > 0) {
                IBS.init(SCCGWA, CICCUST);
                CICCUST.DATA.CI_NO = BPRFEHIS.TX_CI;
                CICCUST.FUNC = 'C';
                CEP.TRC(SCCGWA, CICCUST.DATA.CI_NO);
                S000_CALL_CIZCUST();
            }
            CEP.TRC(SCCGWA, WS_RD_NUM);
            CEP.TRC(SCCGWA, BPRFEHIS.CHG_BR);
            CEP.TRC(SCCGWA, BPRFEHIS.FEE_CODE);
            CEP.TRC(SCCGWA, BPRFEHIS.BSNS_NO);
            CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_TYP);
            if (BPRFEHIS.CHG_BR == BPB1250_AWA_1250.TX_BR 
                || BPRFEHIS.FEE_CODE.equalsIgnoreCase(BPB1250_AWA_1250.FEE_CODE) 
                || BPRFEHIS.BSNS_NO.equalsIgnoreCase(BPB1250_AWA_1250.BSNS_NO)) {
                if (BPB1250_AWA_1250.PC_FLG != ' ') {
                    if ((CICCUST.O_DATA.O_CI_TYP == '1' 
                        && BPB1250_AWA_1250.PC_FLG == 'P') 
                        || (CICCUST.O_DATA.O_CI_TYP == '2' 
                        && BPB1250_AWA_1250.PC_FLG == 'C')) {
                        B020_02_OUT_PAGE_DATA();
                        WS_RD_NUM += 1;
                    }
                } else {
                    B020_02_OUT_PAGE_DATA();
                    WS_RD_NUM += 1;
                }
            }
            T000_READNEXT_BPTFEHIS();
        }
        if (WS_DB_OPEN_FLAG == 'Y') {
            T000_ENDBR_BPTFEHIS();
        }
        CEP.TRC(SCCGWA, WS_RD_NUM);
        WS_RD_NUM = WS_RD_NUM - 1;
        CEP.TRC(SCCGWA, WS_RD_NUM);
        BPCBFEED.OUTPUT_TITLE.CURR_PAGE_ROW = WS_RD_NUM;
        DATA = new BPCBFEED_DATA();
        BPCBFEED.DATA.add(DATA);
        BPCBFEED.OUTPUT_TITLE.CURR_PAGE = BPB1250_AWA_1250.PAGE_NUM;
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCBFEED;
        SCCFMT.DATA_LEN = 35872;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B020_01_OUT_PAGE_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCBFEED.TOTAL_DATA);
        T000_COUNT_BPTFEHIS();
        BPCBFEED.TOTAL_DATA.TOTAL_NUM = WS_COUNT_NO;
        WS_RECORD_NUM = (short) (BPCBFEED.TOTAL_DATA.TOTAL_NUM % BPB1250_AWA_1250.PAGE_ROW);
        BPCBFEED.TOTAL_DATA.TOTAL_PAGE = (int) ((BPCBFEED.TOTAL_DATA.TOTAL_NUM - WS_RECORD_NUM) / BPB1250_AWA_1250.PAGE_ROW);
        CEP.TRC(SCCGWA, BPB1250_AWA_1250.PAGE_ROW);
        CEP.TRC(SCCGWA, BPCBFEED.TOTAL_DATA.TOTAL_NUM);
        CEP.TRC(SCCGWA, BPCBFEED.TOTAL_DATA.TOTAL_PAGE);
        if (WS_RECORD_NUM > 0) {
            BPCBFEED.TOTAL_DATA.TOTAL_PAGE = BPCBFEED.TOTAL_DATA.TOTAL_PAGE + 1;
        }
        IBS.init(SCCGWA, BPCBFEED.OUTPUT_TITLE);
        BPCBFEED.OUTPUT_TITLE.LAST_FLG = 'N';
        if (BPCBFEED.TOTAL_DATA.TOTAL_PAGE == BPB1250_AWA_1250.PAGE_NUM) {
            BPCBFEED.OUTPUT_TITLE.LAST_FLG = 'Y';
        }
        if (BPB1250_AWA_1250.PAGE_NUM > 0) {
            WS_RCD_SEQ = ( BPB1250_AWA_1250.PAGE_NUM - 1 ) * BPB1250_AWA_1250.PAGE_ROW + 1;
        } else {
            WS_RCD_SEQ = 1;
            BPB1250_AWA_1250.PAGE_NUM = 1;
        }
    }
    public void T000_COUNT_BPTFEHIS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRFEHIS.BSNS_NO);
        CEP.TRC(SCCGWA, BPRFEHIS.FEE_CODE);
        if (BPRFEHIS.BSNS_NO.trim().length() > 0 
                && BPRFEHIS.FEE_CODE.trim().length() > 0) {
            CEP.TRC(SCCGWA, "BSNS AND FEE CODE!2");
            BPTFEHIS_RD = new DBParm();
            BPTFEHIS_RD.TableName = "BPTFEHIS";
            BPTFEHIS_RD.set = "WS-COUNT-NO=COUNT(1)";
            BPTFEHIS_RD.where = "CHG_BR = :BPRFEHIS.CHG_BR "
                + "AND BSNS_NO = :BPRFEHIS.BSNS_NO "
                + "AND FEE_CODE = :BPRFEHIS.FEE_CODE "
                + "AND AC_DT BETWEEN :WS_START_DT "
                + "AND :WS_END_DT";
            IBS.GROUP(SCCGWA, BPRFEHIS, this, BPTFEHIS_RD);
        } else if (BPRFEHIS.BSNS_NO.trim().length() > 0 
                && BPRFEHIS.FEE_CODE.trim().length() == 0) {
            CEP.TRC(SCCGWA, "BSNS!1");
            BPTFEHIS_RD = new DBParm();
            BPTFEHIS_RD.TableName = "BPTFEHIS";
            BPTFEHIS_RD.set = "WS-COUNT-NO=COUNT(1)";
            BPTFEHIS_RD.where = "CHG_BR = :BPRFEHIS.CHG_BR "
                + "AND BSNS_NO = :BPRFEHIS.BSNS_NO "
                + "AND AC_DT BETWEEN :WS_START_DT "
                + "AND :WS_END_DT";
            IBS.GROUP(SCCGWA, BPRFEHIS, this, BPTFEHIS_RD);
        } else if (BPRFEHIS.BSNS_NO.trim().length() == 0 
                && BPRFEHIS.FEE_CODE.trim().length() > 0) {
            CEP.TRC(SCCGWA, "FEE CODE!1");
            BPTFEHIS_RD = new DBParm();
            BPTFEHIS_RD.TableName = "BPTFEHIS";
            BPTFEHIS_RD.set = "WS-COUNT-NO=COUNT(1)";
            BPTFEHIS_RD.where = "CHG_BR = :BPRFEHIS.CHG_BR "
                + "AND FEE_CODE = :BPRFEHIS.FEE_CODE "
                + "AND AC_DT BETWEEN :WS_START_DT "
                + "AND :WS_END_DT";
            IBS.GROUP(SCCGWA, BPRFEHIS, this, BPTFEHIS_RD);
        } else {
            CEP.TRC(SCCGWA, "!0");
            BPTFEHIS_RD = new DBParm();
            BPTFEHIS_RD.TableName = "BPTFEHIS";
            BPTFEHIS_RD.set = "WS-COUNT-NO=COUNT(1)";
            BPTFEHIS_RD.where = "CHG_BR = :BPRFEHIS.CHG_BR "
                + "AND AC_DT BETWEEN :WS_START_DT "
                + "AND :WS_END_DT";
            IBS.GROUP(SCCGWA, BPRFEHIS, this, BPTFEHIS_RD);
        }
    }
    public void T000_STARTBR_BPTFEHIS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_START_DT);
        CEP.TRC(SCCGWA, WS_END_DT);
        CEP.TRC(SCCGWA, BPRFEHIS.BSNS_NO);
        CEP.TRC(SCCGWA, BPRFEHIS.FEE_CODE);
        if (BPRFEHIS.BSNS_NO.trim().length() > 0 
                && BPRFEHIS.FEE_CODE.trim().length() > 0) {
            CEP.TRC(SCCGWA, "BSNS AND FEE CODE!2");
            BPTFEHIS_BR.rp = new DBParm();
            BPTFEHIS_BR.rp.TableName = "BPTFEHIS";
            BPTFEHIS_BR.rp.where = "CHG_BR = :BPRFEHIS.CHG_BR "
                + "AND BSNS_NO = :BPRFEHIS.BSNS_NO "
                + "AND FEE_CODE = :BPRFEHIS.FEE_CODE "
                + "AND AC_DT BETWEEN :WS_START_DT "
                + "AND :WS_END_DT";
            IBS.STARTBR(SCCGWA, BPRFEHIS, this, BPTFEHIS_BR);
        } else if (BPRFEHIS.BSNS_NO.trim().length() > 0 
                && BPRFEHIS.FEE_CODE.trim().length() == 0) {
            CEP.TRC(SCCGWA, "BSNS!1");
            BPTFEHIS_BR.rp = new DBParm();
            BPTFEHIS_BR.rp.TableName = "BPTFEHIS";
            BPTFEHIS_BR.rp.where = "CHG_BR = :BPRFEHIS.CHG_BR "
                + "AND BSNS_NO = :BPRFEHIS.BSNS_NO "
                + "AND AC_DT BETWEEN :WS_START_DT "
                + "AND :WS_END_DT";
            IBS.STARTBR(SCCGWA, BPRFEHIS, this, BPTFEHIS_BR);
        } else if (BPRFEHIS.BSNS_NO.trim().length() == 0 
                && BPRFEHIS.FEE_CODE.trim().length() > 0) {
            CEP.TRC(SCCGWA, "FEE CODE!1");
            BPTFEHIS_BR.rp = new DBParm();
            BPTFEHIS_BR.rp.TableName = "BPTFEHIS";
            BPTFEHIS_BR.rp.where = "CHG_BR = :BPRFEHIS.CHG_BR "
                + "AND FEE_CODE = :BPRFEHIS.FEE_CODE "
                + "AND AC_DT BETWEEN :WS_START_DT "
                + "AND :WS_END_DT";
            IBS.STARTBR(SCCGWA, BPRFEHIS, this, BPTFEHIS_BR);
        } else {
            CEP.TRC(SCCGWA, "!0");
            BPTFEHIS_BR.rp = new DBParm();
            BPTFEHIS_BR.rp.TableName = "BPTFEHIS";
            BPTFEHIS_BR.rp.where = "CHG_BR = :BPRFEHIS.CHG_BR "
                + "AND AC_DT BETWEEN :WS_START_DT "
                + "AND :WS_END_DT";
            IBS.STARTBR(SCCGWA, BPRFEHIS, this, BPTFEHIS_BR);
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DB_FLAG = 'F';
            WS_DB_OPEN_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_DB_FLAG = 'N';
        } else {
        }
    }
    public void T000_READNEXT_BPTFEHIS_FIRST() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRFEHIS, this, BPTFEHIS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DB_FLAG = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_DB_FLAG = 'N';
        } else {
        }
    }
    public void T000_READNEXT_BPTFEHIS() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRFEHIS, this, BPTFEHIS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DB_FLAG = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_DB_FLAG = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPTFEHIS() throws IOException,SQLException,Exception {
        BPTFEHIS_BR.rp.errhdl = true;
        IBS.ENDBR(SCCGWA, BPTFEHIS_BR);
    }
    public void B020_02_OUT_PAGE_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DATA);
        CEP.TRC(SCCGWA, WS_RD_NUM);
        if (BPRFEHIS.BSNS_NO.trim().length() > 0) {
            IBS.init(SCCGWA, LNCRAGRE);
            IBS.init(SCCGWA, LNRAGRE);
            LNCRAGRE.FUNC = 'I';
            LNRAGRE.KEY.CONTRACT_NO = BPRFEHIS.BSNS_NO;
            S000_CALL_LNZRAGRE();
            CEP.TRC(SCCGWA, LNRAGRE.KEY.CONTRACT_NO);
        }
        if (BPRFEHIS.CHG_AC.trim().length() > 0) {
            IBS.init(SCCGWA, DDCIMMST);
            DDCIMMST.DATA.KEY.AC_NO = BPRFEHIS.CHG_AC;
            DDCIMMST.TX_TYPE = 'I';
            S000_CALL_DDZIMMST();
            CEP.TRC(SCCGWA, DDCIMMST.DATA.OPEN_DP);
            DATA.P_OPN_BR = DDCIMMST.DATA.OPEN_DP;
            CEP.TRC(SCCGWA, DDCIMMST.DATA.OPEN_DP);
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
            BPCPQORG.BR = DDCIMMST.DATA.OPEN_DP;
            S000_CALL_BPZPQORG();
            DATA.P_OPN_ABBR = BPCPQORG.CHN_NM;
        }
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
        BPCPQORG.BR = BPRFEHIS.CHG_BR;
        S000_CALL_BPZPQORG();
        DATA.TX_ABBR = BPCPQORG.CHN_NM;
        DATA.R_ABBR = BPCPQORG.CHN_NM;
        DATA.TX_DT = BPRFEHIS.KEY.AC_DT;
        DATA.JRN_NO = BPRFEHIS.KEY.JRNNO;
        DATA.JRN_SEQ = BPRFEHIS.KEY.JRN_SEQ;
        DATA.BSNS_NO = BPRFEHIS.BSNS_NO;
        DATA.TX_BR = BPRFEHIS.CHG_BR;
        DATA.FEE_CODE = BPRFEHIS.FEE_CODE;
        DATA.CHG_TYPE = BPRFEHIS.CHG_AC_TY;
        DATA.TX_CI_CNM = CICCUST.O_DATA.O_CI_NM;
        DATA.CHG_AC = BPRFEHIS.CHG_AC;
        DATA.R_BR = BPRFEHIS.CHG_BR;
        DATA.CHG_AMT = BPRFEHIS.CHG_AMT;
        DATA.CHG_CCY = BPRFEHIS.CHG_CCY;
        DATA.STS = BPRFEHIS.TX_STS;
        DATA.REMARK = BPRFEHIS.REMARK;
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_NM);
    }
    public void S000_CALL_LNZRAGRE() throws IOException,SQLException,Exception {
        LNCRAGRE.REC_PTR = LNRAGRE;
        LNCRAGRE.REC_LEN = 203;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTAGRE", LNCRAGRE);
        if (LNCRAGRE.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRAGRE.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DDZIMMST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-NFIN-M-MST", DDCIMMST);
        if (DDCIMMST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIMMST.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        CEP.TRC(SCCGWA, BPCPQORG.RC.RC_CODE);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            WS_FLD_NO = (short) BPB1250_AWA_1250.TX_BR;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_ERR_INFO, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
