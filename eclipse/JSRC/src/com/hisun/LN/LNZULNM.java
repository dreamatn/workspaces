package com.hisun.LN;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCACLDD;
import com.hisun.BP.BPCCGAC;
import com.hisun.BP.BPCPQORG;
import com.hisun.BP.BPCPQPRD;
import com.hisun.BP.BPCQCCY;
import com.hisun.CI.CICCUST;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCLDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGWA;

public class LNZULNM {
    boolean pgmRtn = false;
    String PGM_SCSSCLDT = "SCSSCLDT";
    char WS_CCY_ID = ' ';
    char WS_PAY_MTH = ' ';
    int WS_I = 0;
    int WS_LEN = 0;
    int WS_OLD_VAL_DT = 0;
    String WS_FORM_CODE = " ";
    double WS_N_RATE = 0;
    double WS_PRIN_AMT = 0;
    double WS_TOT_PRIN_AMT = 0;
    char WS_INST_MTH = ' ';
    char WS_REP_OPT = ' ';
    double WS_INST_AMT = 0;
    short WS_INST_TERM = 0;
    short WS_TMP_INST_TERM = 0;
    short WS_PHS_NO = 0;
    LNZULNM_WS_PRM_KEY WS_PRM_KEY = new LNZULNM_WS_PRM_KEY();
    char WS_UPDATE_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    char WS_DRAW_FLG = ' ';
    char WS_INST_FLG = ' ';
    char WS_FWDH_FOUND_FLG = ' ';
    char WS_CMMT_ACT_FLG = ' ';
    char WS_RELA_ACT_FLG = ' ';
    LNRPAIP LNRPAIP = new LNRPAIP();
    LNRRATN LNRRATN = new LNRRATN();
    LNRRATL LNRRATL = new LNRRATL();
    LNRFWDH LNRFWDH = new LNRFWDH();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCCLDT SCCCLDT = new SCCCLDT();
    LNCAPRDM LNCAPRDM = new LNCAPRDM();
    LNCCONTM LNCCONTM = new LNCCONTM();
    LNCLOANM LNCLOANM = new LNCLOANM();
    LNCICTLM LNCICTLM = new LNCICTLM();
    LNCPLPIM LNCPLPIM = new LNCPLPIM();
    LNCRATNM LNCRATNM = new LNCRATNM();
    LNCRATLM LNCRATLM = new LNCRATLM();
    LNCILCM LNCILCM = new LNCILCM();
    LNCPAIPM LNCPAIPM = new LNCPAIPM();
    LNCRATG LNCRATG = new LNCRATG();
    BPCCGAC BPCCGAC = new BPCCGAC();
    BPCACLDD BPCACLDD = new BPCACLDD();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    BPCPQORG BPCPQORG = new BPCPQORG();
    CICCUST CICCUST = new CICCUST();
    BPCQCCY BPCQCCY = new BPCQCCY();
    LNRCMMT LNRCMMT = new LNRCMMT();
    LNCRCMMT LNCRCMMT = new LNCRCMMT();
    LNRICTL LNRICTL = new LNRICTL();
    LNCRICTL LNCRICTL = new LNCRICTL();
    LNRRELA LNRRELA = new LNRRELA();
    LNCPAYSM LNCPAYSM = new LNCPAYSM();
    SCCGWA SCCGWA;
    LNCULNC LNCULNM;
    public void MP(SCCGWA SCCGWA, LNCULNC LNCULNM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCULNM = LNCULNM;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZULNM return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNCULNM.RC.RC_APP = "LN";
        LNCULNM.RC.RC_RTNCODE = 0;
        CEP.TRC(SCCGWA, LNCULNM.COMM_DATA.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNCULNM.COMM_DATA.INT_DAY_BAS);
        CEP.TRC(SCCGWA, LNCULNM.COMM_DATA.ACCRUAL_TYPE);
        CEP.TRC(SCCGWA, LNCULNM.COMM_DATA.REMARK1);
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B000_CHECK() throws IOException,SQLException,Exception {
        if (LNCULNM.COMM_DATA.VAL_DT > LNCULNM.COMM_DATA.DUE_DT) {
