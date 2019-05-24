package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPPRDQ {
    String JIBS_tmp_str[] = new String[10];
    String CPN_PARM_MT = "BP-PARM-MAINTAIN    ";
    char K_ALL = '4';
    BPZPPRDQ_WS_CPROD_KEY WS_CPROD_KEY = new BPZPPRDQ_WS_CPROD_KEY();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPRCPROD BPRCPROD = new BPRCPROD();
    BPRPROD BPRPROD = new BPRPROD();
    SCCGWA SCCGWA;
    BPCPPRDQ BPCPPRDQ;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCPPRDQ BPCPPRDQ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPPRDQ = BPCPPRDQ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZPPRDQ return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, BPCPPRDQ.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPPRDQ);
        B010_CHECK_INPUT();
        B020_QUERY_PROCESS();
        R000_TRANS_DATA_OUTPUT();
        CEP.TRC(SCCGWA, BPCPPRDQ);
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCPPRDQ.DATA_INFO.CCY.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_INPUT_CCY, BPCPPRDQ.RC);
        }
        if (BPCPPRDQ.DATA_INFO.STAT != '1' 
            && BPCPPRDQ.DATA_INFO.STAT != '0' 
            && BPCPPRDQ.DATA_INFO.STAT != '2' 
            && BPCPPRDQ.DATA_INFO.STAT != '3') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_STAT_MUST_INPUT, BPCPPRDQ.RC);
        }
        if (BPCPPRDQ.DATA_INFO.CS_KIND != '0' 
            && BPCPPRDQ.DATA_INFO.CS_KIND != '1' 
            && BPCPPRDQ.DATA_INFO.CS_KIND != '2' 
            && BPCPPRDQ.DATA_INFO.CS_KIND != '3') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CS_KIND_MUST_INPUT, BPCPPRDQ.RC);
        }
    }
    public void B020_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, BPRCPROD);
        CEP.TRC(SCCGWA, BPCPRMM);
        CEP.TRC(SCCGWA, BPRCPROD);
        BPCPRMM.FUNC = '3';
        BPCPRMM.EFF_DT = BPCPPRDQ.DATA_INFO.EFF_DATE;
        WS_CPROD_KEY.WS_CPROD_CCY = BPCPPRDQ.DATA_INFO.CCY;
        WS_CPROD_KEY.WS_CPROD_STAT = BPCPPRDQ.DATA_INFO.STAT;
        WS_CPROD_KEY.WS_CPROD_CS_KIND = BPCPPRDQ.DATA_INFO.CS_KIND;
        BPRCPROD.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_CPROD_KEY);
        BPRCPROD.KEY.TYP = "CPROD";
        CEP.TRC(SCCGWA, BPCPRMM);
        CEP.TRC(SCCGWA, BPRCPROD);
        BPCPRMM.DAT_PTR = BPRCPROD;
        S000_CALL_BPZPRMM();
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            WS_CPROD_KEY.WS_CPROD_STAT = K_ALL;
            BPRCPROD.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_CPROD_KEY);
            BPCPRMM.DAT_PTR = BPRCPROD;
            S000_CALL_BPZPRMM();
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
                WS_CPROD_KEY.WS_CPROD_STAT = BPCPPRDQ.DATA_INFO.STAT;
                WS_CPROD_KEY.WS_CPROD_CS_KIND = K_ALL;
                BPRCPROD.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_CPROD_KEY);
                BPCPRMM.DAT_PTR = BPRCPROD;
                S000_CALL_BPZPRMM();
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
                if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
                    WS_CPROD_KEY.WS_CPROD_STAT = K_ALL;
                    WS_CPROD_KEY.WS_CPROD_CS_KIND = K_ALL;
                    BPRCPROD.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_CPROD_KEY);
                    BPCPRMM.DAT_PTR = BPRCPROD;
                    S000_CALL_BPZPRMM();
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
                    if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
                        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PROD_NOTFND, BPCPPRDQ.RC);
                    }
                }
            }
        }
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPRCPROD.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_CPROD_KEY);
        BPRCPROD.DATA_TXT.ACCT_CD = BPCPPRDQ.DATA_INFO.ACCT_CD;
        BPRCPROD.DATA_TXT.PL_CD = BPCPPRDQ.DATA_INFO.PL_CD;
        BPRCPROD.DATA_TXT.AC_SEQ = BPCPPRDQ.DATA_INFO.AC_SEQ;
        BPRCPROD.DATA_TXT.ACCT_LVL = BPCPPRDQ.DATA_INFO.ACCT_LVL;
        BPRCPROD.DATA_TXT.ACCT_TYP = BPCPPRDQ.DATA_INFO.ACCT_TYP;
        BPRCPROD.DATA_TXT.SUP_FLG = BPCPPRDQ.DATA_INFO.SUP_FLG;
    }
    public void R000_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        BPCPPRDQ.DATA_INFO.ACCT_CD = BPRCPROD.DATA_TXT.ACCT_CD;
        BPCPPRDQ.DATA_INFO.PL_CD = BPRCPROD.DATA_TXT.PL_CD;
        BPCPPRDQ.DATA_INFO.AC_SEQ = BPRCPROD.DATA_TXT.AC_SEQ;
        BPCPPRDQ.DATA_INFO.ACCT_LVL = BPRCPROD.DATA_TXT.ACCT_LVL;
        BPCPPRDQ.DATA_INFO.ACCT_TYP = BPRCPROD.DATA_TXT.ACCT_TYP;
        BPCPPRDQ.DATA_INFO.SUP_FLG = BPRCPROD.DATA_TXT.SUP_FLG;
        BPCPPRDQ.DATA_INFO.EFF_DATE = BPRCPROD.DATA_TXT.EFF_DATE;
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_PARM_MT, BPCPRMM);
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPPRDQ.RC);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCPPRDQ.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCPPRDQ = ");
            CEP.TRC(SCCGWA, BPCPPRDQ);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
