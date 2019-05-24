package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPQPPR {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int K_SPECIAL_ACCT = 999999;
    String K_OUTPUT_FMT = "BPZ08";
    String K_CMP_CHK_CAL_CODE = "BP-P-CHK-CAL-CODE";
    String K_R_IDEV_MAINT = "BP-R-IDEV-MAINT";
    String K_PARM_MAINTAIN = "BP-PARM-MAINTAIN";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String K_PRDPR_TYPE = "PRDPR";
    String K_TENOR = "RTENO";
    String BP_QPCD_MAIN = "BP-P-INQ-PC";
    short WS_I = 0;
    short WS_J = 0;
    short WS_K = 0;
    BPZPQPPR_WS_CODE WS_CODE = new BPZPQPPR_WS_CODE();
    BPZPQPPR_WS_OUTPUT_DATA WS_OUTPUT_DATA = new BPZPQPPR_WS_OUTPUT_DATA();
    char WS_OUTPUT_FLG = ' ';
    char WS_STOP_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCGWA SCCGWA;
    BPCPQPPR BPCPQPPR;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCPQPPR BPCPQPPR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPQPPR = BPCPQPPR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPQPPR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCPQPPR.RC);
        IBS.init(SCCGWA, WS_OUTPUT_DATA);
        BPCPQPPR.RC.RC_AP = "BP";
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B210_KEY_PROCESS();
        if (pgmRtn) return;
        B300_MOVE_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void B210_KEY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPRMT);
        IBS.init(SCCGWA, BPCPRMM);
        BPRPRMT.KEY.TYP = K_PRDPR_TYPE;
        WS_CODE.WS_ACC_CENT = BPCPQPPR.ACC_CENT;
        WS_CODE.WS_PROD_CD = BPCPQPPR.PRDT_CODE;
        BPRPRMT.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_CODE);
        CEP.TRC(SCCGWA, BPRPRMT.KEY.TYP);
        CEP.TRC(SCCGWA, BPRPRMT.KEY.CD);
        CEP.TRC(SCCGWA, BPCPQPPR.ACC_CENT);
        CEP.TRC(SCCGWA, BPCPQPPR.PRDT_CODE);
        BPCPRMM.FUNC = '3';
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        R000_CHECK_RETURN_1();
        if (pgmRtn) return;
    }
    public void R000_CHECK_RETURN_1() throws IOException,SQLException,Exception {
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "CHECK ACC-CENT=0");
            if (BPCPQPPR.ACC_CENT == K_SPECIAL_ACCT) {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPQPPR.RC);
            }
            if (BPCPQPPR.ACC_CENT != K_SPECIAL_ACCT) {
                IBS.init(SCCGWA, BPRPRMT);
                IBS.init(SCCGWA, BPCPRMM);
                BPRPRMT.KEY.TYP = K_PRDPR_TYPE;
                WS_CODE.WS_ACC_CENT = K_SPECIAL_ACCT;
                WS_CODE.WS_PROD_CD = BPCPQPPR.PRDT_CODE;
                BPRPRMT.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_CODE);
                BPCPRMM.FUNC = '3';
                S000_CALL_BPZPRMM();
                if (pgmRtn) return;
                if (BPCPRMM.RC.RC_RTNCODE != 0) {
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
                    IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPQPPR.RC);
                }
            }
        }
    }
    public void B300_MOVE_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.CPY2CLS(SCCGWA, BPRPRMT.KEY.CD, WS_CODE);
        BPCPQPPR.ACC_CENT = WS_CODE.WS_ACC_CENT;
        BPCPQPPR.PRDT_CODE = WS_CODE.WS_PROD_CD;
        BPCPQPPR.PARM_NAME = BPRPRMT.DESC;
        BPCPQPPR.PARM_DATA = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        BPCPRMM.DAT_PTR = BPRPRMT;
        IBS.CALLCPN(SCCGWA, K_PARM_MAINTAIN, BPCPRMM);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQPPR.RC);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
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
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCPQPPR.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCPQPPR = ");
            CEP.TRC(SCCGWA, BPCPQPPR);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
