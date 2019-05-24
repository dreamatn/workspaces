package com.hisun.CI;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCSGSEQ;
import com.hisun.BP.BPRORGM;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class CIZCINO {
    boolean pgmRtn = false;
    String WS_CI_NO = " ";
    CIZCINO_REDEFINES2 REDEFINES2 = new CIZCINO_REDEFINES2();
    short WS_I = 0;
    short WS_RET = 0;
    int WS_SEQ = 0;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    BPRORGM BPRORGM = new BPRORGM();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICCINO CICCINO;
    public void MP(SCCGWA SCCGWA, CICCINO CICCINO) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICCINO = CICCINO;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZCINO return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICCINO.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_CREATE_CI_NO();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
    }
    public void B020_CREATE_CI_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRORGM);
        BPRORGM.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPTORGM_RD = new DBParm();
        BPTORGM_RD.TableName = "BPTORGM";
        IBS.READ(SCCGWA, BPRORGM, BPTORGM_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            if (WS_CI_NO == null) WS_CI_NO = "";
            JIBS_tmp_int = WS_CI_NO.length();
            for (int i=0;i<12-JIBS_tmp_int;i++) WS_CI_NO += " ";
            if (BPRORGM.VIL_TYP == null) BPRORGM.VIL_TYP = "";
            JIBS_tmp_int = BPRORGM.VIL_TYP.length();
            for (int i=0;i<3-JIBS_tmp_int;i++) BPRORGM.VIL_TYP += " ";
            WS_CI_NO = BPRORGM.VIL_TYP + WS_CI_NO.substring(3);
            IBS.CPY2CLS(SCCGWA, WS_CI_NO, REDEFINES2);
        }
        IBS.init(SCCGWA, BPCSGSEQ);
        BPCSGSEQ.TYPE = "SEQ";
        BPCSGSEQ.CODE = "CINOSEQ";
        BPCSGSEQ.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSGSEQ.RUN_MODE = 'O';
        S000_CALL_BPZSGSEQ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCSGSEQ.SEQ);
        WS_SEQ = (int) BPCSGSEQ.SEQ;
        if (WS_CI_NO == null) WS_CI_NO = "";
        JIBS_tmp_int = WS_CI_NO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) WS_CI_NO += " ";
        JIBS_tmp_str[0] = "" + WS_SEQ;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        WS_CI_NO = WS_CI_NO.substring(0, 4 - 1) + JIBS_tmp_str[0] + WS_CI_NO.substring(4 + 8 - 1);
        IBS.CPY2CLS(SCCGWA, WS_CI_NO, REDEFINES2);
        CEP.TRC(SCCGWA, WS_CI_NO);
        WS_I = (short) (REDEFINES2.WS_CI_NO1 * 19 + REDEFINES2.WS_CI_NO2 * 23 + REDEFINES2.WS_CI_NO3 * 29 + REDEFINES2.WS_CI_NO4 * 29 + REDEFINES2.WS_CI_NO5 * 2 + REDEFINES2.WS_CI_NO6 * 3 + REDEFINES2.WS_CI_NO7 * 5 + REDEFINES2.WS_CI_NO8 * 7 + REDEFINES2.WS_CI_NO9 * 11 + REDEFINES2.WS_CI_NO10 * 13 + REDEFINES2.WS_CI_NO11 * 17);
        CEP.TRC(SCCGWA, WS_I);
