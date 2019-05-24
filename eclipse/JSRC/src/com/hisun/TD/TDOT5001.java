package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class TDOT5001 {
    String JIBS_tmp_str[] = new String[10];
    String K_AP_MMO = "TD";
    String K_PRD_FMT = "TD500";
    String K_PRDP_TYP = "PRDPR";
    String K_HIS_FMT = "TDCPRDP";
    String K_HIS_RMK = "TD PRODUCT PARM MAINTENANCE";
    String K_SYS_ERR = "SC6001";
    String K_TIRUL_TYP = "TIRUL";
    String WS_MSGID = " ";
    TDOT5001_WS_TERM1 WS_TERM1 = new TDOT5001_WS_TERM1();
    TDOT5001_WS_TERM2 WS_TERM2 = new TDOT5001_WS_TERM2();
    TDOT5001_WS_TERM3 WS_TERM3 = new TDOT5001_WS_TERM3();
    TDOT5001_WS_TERM4 WS_TERM4 = new TDOT5001_WS_TERM4();
    TDOT5001_WS_TERM5 WS_TERM5 = new TDOT5001_WS_TERM5();
    TDOT5001_WS_TERM6 WS_TERM6 = new TDOT5001_WS_TERM6();
    short WS_I = 0;
    TDOT5001_WS_IRAT_INFO WS_IRAT_INFO = new TDOT5001_WS_IRAT_INFO();
    TDOT5001_CP_PROD_CD CP_PROD_CD = new TDOT5001_CP_PROD_CD();
    short WS_CNT = 0;
    short WS_CNT2 = 0;
    char WS_CCY_FOUND_FLG = ' ';
    String WS_INT_RUL_CD = " ";
    String WS_MIN_CCY = " ";
    String WS_TENOR = " ";
    short WS_X = 0;
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    TDCSRDMA TDCSRDMA = new TDCSRDMA();
    TDCPRDP TDCPRDP = new TDCPRDP();
    TDCQPOD TDCQPOD = new TDCQPOD();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    TDCIRULP TDCIRULP = new TDCIRULP();
    SCCGWA SCCGWA;
    TDCPROD TDCPROD;
    public void MP(SCCGWA SCCGWA, TDCPROD TDCPROD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCPROD = TDCPROD;
        CEP.TRC(SCCGWA);
        CEP.TRC(SCCGWA, "JIFEI1");
        CEP.TRC(SCCGWA, TDCPROD.FUNC);
        CEP.TRC(SCCGWA, TDCPROD.EFF_DT);
        CEP.TRC(SCCGWA, TDCPROD);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDOT5001 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCPROD);
        B010_CHECK_INPUT();
        CEP.TRC(SCCGWA, "JIFEI2");
        B050_WRITE_DATA();
        CEP.TRC(SCCGWA, "JIFEI3");
        B210_WRT_NHIS_A();
        CEP.TRC(SCCGWA, "JIFEI4");
        B300_OUTPUT_INF();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (TDCPROD.PRD_CD.trim().length() == 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_PRD_NOT_MATCH;
            S000_ERR_MSG_PROC();
        }
    }
    public void B050_WRITE_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, BPRPRMT);
        TDCPRDP.PRDMO_CD = TDCPROD.PRDO_CDM;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.TXN_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.TXN_PRM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.INT_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.INT_PRM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.EXP_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.EXP_PRM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.OTH_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.OTH_PRM);
        BPCPRMM.FUNC = '0';
        CP_PROD_CD.PROD_ACC_CENT = 999999;
        CP_PROD_CD.PROD_PRDT_CODE = TDCPROD.PRD_CD;
        BPRPRMT.KEY.CD = IBS.CLS2CPY(SCCGWA, CP_PROD_CD);
        CEP.TRC(SCCGWA, "AAAAAAAA:");
        CEP.TRC(SCCGWA, BPRPRMT.KEY.CD);
        BPRPRMT.KEY.TYP = "PRDPR";
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPRDP);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRPRMT.DAT_TXT);
        BPRPRMT.DESC = TDCPROD.DESC;
        BPRPRMT.CDESC = TDCPROD.CDESC;
        BPCPRMM.EFF_DT = TDCPROD.EFF_DT;
        BPCPRMM.EXP_DT = TDCPROD.EXP_DT;
        BPCPRMM.DAT_PTR = BPRPRMT;
        S000_CALL_BPZPRMM();
    }
    public void B090_CHECK_IRU() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, BPRPRMT);
        BPCPRMM.FUNC = '3';
        BPRPRMT.KEY.TYP = K_TIRUL_TYP;
        BPRPRMT.KEY.CD = WS_INT_RUL_CD;
        BPCPRMM.EFF_DT = TDCPROD.EFF_DT;
        BPCPRMM.DAT_PTR = BPRPRMT;
        S000_CALL_BPZPRMM();
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCIRULP);
        if (TDCIRULP.SPRD_OPT == 'A') {
        } else {
            B091_CHECK_CCY();
        }
    }
    public void B091_CHECK_CCY() throws IOException,SQLException,Exception {
        WS_CCY_FOUND_FLG = 'N';
    }
    public void B110_CHECK_CCY() throws IOException,SQLException,Exception {
    }
    public void B120_CHECK_CCY() throws IOException,SQLException,Exception {
    }
    public void B210_WRT_NHIS_A() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.REF_NO = TDCPROD.PRD_CD;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.FMT_ID = "TDCPRDP";
        BPCPNHIS.INFO.TX_RMK = "TD PRODUCT PARM MAINTENANCE";
        BPCPNHIS.INFO.NEW_DAT_PT = TDCPRDP;
        S000_CALL_BPZPNHIS();
    }
    public void B300_OUTPUT_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCQPOD);
        TDCQPOD.FUNC = 'A';
        TDCQPOD.PROD_CD = TDCPROD.PRD_CD;
        TDCQPOD.DESC = BPRPRMT.DESC;
        TDCQPOD.CDESC = BPRPRMT.CDESC;
        TDCQPOD.EFF_DT = BPCPRMM.EFF_DT;
        TDCQPOD.EXP_DT = BPCPRMM.EXP_DT;
        TDCQPOD.PRDMO_CD = TDCPRDP.PRDMO_CD;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPRDP.TXN_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCQPOD.TXN_PRM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPRDP.INT_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCQPOD.INT_PRM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPRDP.EXP_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCQPOD.EXP_PRM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPRDP.OTH_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCQPOD.OTH_PRM);
        CEP.TRC(SCCGWA, TDCQPOD.FUNC);
        CEP.TRC(SCCGWA, TDCQPOD.PROD_CD);
        CEP.TRC(SCCGWA, TDCQPOD.DESC);
        CEP.TRC(SCCGWA, TDCQPOD.CDESC);
        CEP.TRC(SCCGWA, TDCQPOD.EFF_DT);
        CEP.TRC(SCCGWA, TDCQPOD.EXP_DT);
        CEP.TRC(SCCGWA, TDCQPOD.PRDMO_CD);
        CEP.TRC(SCCGWA, TDCQPOD.TXN_PRM);
        CEP.TRC(SCCGWA, TDCQPOD.INT_PRM);
        CEP.TRC(SCCGWA, TDCQPOD.EXP_PRM);
        CEP.TRC(SCCGWA, TDCQPOD.OTH_PRM);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "TD500";
        SCCFMT.DATA_PTR = TDCQPOD;
        SCCFMT.DATA_LEN = 5726;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
        CEP.TRC(SCCGWA, BPCPRMM.RC);
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            if (BPRPRMT.KEY.TYP.equalsIgnoreCase("TIRUL")) {
                WS_MSGID = TDCMSG_ERROR_MSG.TD_N_EXSIT_IRUL;
            } else {
                WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            }
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID, SCCBINF);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
