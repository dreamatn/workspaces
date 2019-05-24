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

public class TDOT5002 {
    String JIBS_tmp_str[] = new String[10];
    String K_AP_MMO = "TD";
    String K_PRD_FMT = "TD500";
    String K_PRDP_TYP = "PRDPR";
    String K_HIS_FMT = "TDCPRDP";
    String K_HIS_RMK = "TD PRODUCT PARM MAINTENANCE";
    String K_SYS_ERR = "SC6001";
    String WS_MSGID = " ";
    short WS_I = 0;
    TDOT5002_WS_IRAT_INFO WS_IRAT_INFO = new TDOT5002_WS_IRAT_INFO();
    TDOT5002_WS_TERM1 WS_TERM1 = new TDOT5002_WS_TERM1();
    TDOT5002_WS_TERM2 WS_TERM2 = new TDOT5002_WS_TERM2();
    TDOT5002_WS_TERM3 WS_TERM3 = new TDOT5002_WS_TERM3();
    TDOT5002_WS_TERM4 WS_TERM4 = new TDOT5002_WS_TERM4();
    TDOT5002_WS_TERM5 WS_TERM5 = new TDOT5002_WS_TERM5();
    TDOT5002_WS_TERM6 WS_TERM6 = new TDOT5002_WS_TERM6();
    TDOT5002_CP_PROD_CD CP_PROD_CD = new TDOT5002_CP_PROD_CD();
    short WS_CNT = 0;
    short WS_CNT2 = 0;
    char WS_CCY_FOUND_FLG = ' ';
    String WS_INT_RUL_CD = " ";
    String WS_MIN_CCY = " ";
    String WS_TENOR = " ";
    short WS_X = 0;
    TDOT5002_WS_TERM[] WS_TERM = new TDOT5002_WS_TERM[6];
    short WS_IDX = 0;
    TDOT5002_WDS_CCYS WDS_CCYS = new TDOT5002_WDS_CCYS();
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
    TDCPRDP TDCPRDPO = new TDCPRDP();
    BPCCINTI BPCCINTI = new BPCCINTI();
    SCCGWA SCCGWA;
    TDCPROD TDCPROD;
    public TDOT5002() {
        for (int i=0;i<6;i++) WS_TERM[i] = new TDOT5002_WS_TERM();
    }
    public void MP(SCCGWA SCCGWA, TDCPROD TDCPROD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCPROD = TDCPROD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDOT5002 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B130_DEL_PRD_PARM();
        B230_WRT_NHIS_D();
        B300_OUTPUT_INF();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (TDCPROD.PRD_CD.trim().length() == 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_PRD_NOT_MATCH;
            S000_ERR_MSG_PROC();
        }
        if (TDCPROD.EFF_DT == 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_EFF_DATE_ERROR;
            S000_ERR_MSG_PROC();
        }
    }
    public void B130_DEL_PRD_PARM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCPROD.PRD_CD);
        CEP.TRC(SCCGWA, TDCPROD.EFF_DT);
        IBS.init(SCCGWA, BPCPRMM);
        BPCPRMM.FUNC = '1';
        BPRPRMT.KEY.TYP = K_PRDP_TYP;
        CP_PROD_CD.PROD_ACC_CENT = 999999999;
        CP_PROD_CD.PROD_PRDT_CODE = TDCPROD.PRD_CD;
        BPRPRMT.KEY.CD = IBS.CLS2CPY(SCCGWA, CP_PROD_CD);
        BPCPRMM.EFF_DT = TDCPROD.EFF_DT;
        BPCPRMM.DAT_PTR = BPRPRMT;
        CEP.TRC(SCCGWA, BPRPRMT.KEY.TYP);
        CEP.TRC(SCCGWA, BPRPRMT.KEY.CD);
        S000_CALL_BPZPRMM();
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDPO);
    }
    public void B230_WRT_NHIS_D() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'D';
        BPCPNHIS.INFO.REF_NO = TDCPROD.PRD_CD;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.FMT_ID = K_HIS_FMT;
        BPCPNHIS.INFO.TX_RMK = K_HIS_RMK;
        BPCPNHIS.INFO.OLD_DAT_PT = TDCPRDPO;
        S000_CALL_BPZPNHIS();
    }
    public void B300_OUTPUT_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCQPOD);
        TDCQPOD.FUNC = 'D';
        TDCQPOD.PROD_CD = TDCPROD.PRD_CD;
        TDCQPOD.DESC = TDCPROD.DESC;
        TDCQPOD.CDESC = TDCPROD.CDESC;
        TDCQPOD.EFF_DT = TDCPROD.EFF_DT;
        TDCQPOD.EXP_DT = TDCPROD.EXP_DT;
        TDCQPOD.PRDMO_CD = TDCPROD.PRDO_CDM;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.TXN_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCQPOD.TXN_PRM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.INT_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCQPOD.INT_PRM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.EXP_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCQPOD.EXP_PRM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.OTH_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCQPOD.OTH_PRM);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_PRD_FMT;
        SCCFMT.DATA_PTR = TDCQPOD;
        SCCFMT.DATA_LEN = 5726;
        CEP.TRC(SCCGWA, TDCQPOD);
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
        CEP.TRC(SCCGWA, BPCPRMM.RC);
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
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
