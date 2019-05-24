package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZQCNCI {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZQCNCI";
    String CPN_BP_I_INQ_CNTY = "BP-I-INQ-CNTY";
    String CPN_BP_I_INQ_CITY = "BP-I-INQ-CITY";
    String WS_ERR_MSG = " ";
    int WS_EFF_DT = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPCIQCIT BPCIQCIT = new BPCIQCIT();
    BPCIQCNT BPCIQCNT = new BPCIQCNT();
    SCCGWA SCCGWA;
    BPCQCNCI BPCQCNCI;
    public void MP(SCCGWA SCCGWA, BPCQCNCI BPCQCNCI) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCQCNCI = BPCQCNCI;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZQCNCI return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCIQCIT);
        IBS.init(SCCGWA, BPCIQCNT);
        IBS.CPY2CLS(SCCGWA, "BP0000", BPCQCNCI.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCQCNCI);
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_QUERY_CNTY_INFO();
        if (pgmRtn) return;
        B030_QUERY_CITY_INFO();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCQCNCI.INPUT_DAT.CNTY_CD.trim().length() == 0 
            && BPCQCNCI.INPUT_DAT.CITY_CD.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_INPUT_CNTY_CD, BPCQCNCI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCQCNCI.INPUT_DAT.CNTY_CD.trim().length() == 0 
            && BPCQCNCI.INPUT_DAT.CITY_CD.trim().length() > 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_INPUT_CNTY_CD, BPCQCNCI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_QUERY_CNTY_INFO() throws IOException,SQLException,Exception {
        if (BPCQCNCI.INPUT_DAT.CNTY_CD.trim().length() > 0) {
            IBS.init(SCCGWA, BPCIQCNT);
            BPCIQCNT.INPUT_DAT.CNTY_CD = BPCQCNCI.INPUT_DAT.CNTY_CD;
            S000_CALL_BPZIQCNT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCIQCNT);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCIQCNT.OUTPUT_DAT);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCQCNCI.OUTPUT_CNTY_DAT);
        }
        CEP.TRC(SCCGWA, BPCIQCNT.OUTPUT_DAT);
        BPCQCNCI.OUTPUT_CAL_CODE = BPCQCNCI.OUTPUT_CNTY_DAT.CALR_CODE;
    }
    public void B030_QUERY_CITY_INFO() throws IOException,SQLException,Exception {
        if (BPCQCNCI.INPUT_DAT.CNTY_CD.trim().length() > 0 
            && BPCQCNCI.INPUT_DAT.CITY_CD.trim().length() > 0) {
            BPCIQCIT.INPUT_DAT.CNTY_CD = BPCQCNCI.INPUT_DAT.CNTY_CD;
            BPCIQCIT.INPUT_DAT.CITY_CD = BPCQCNCI.INPUT_DAT.CITY_CD;
            S000_CALL_BPZIQCIT();
            if (pgmRtn) return;
            if (BPCIQCIT.RC.RC_CODE != 0) {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCIQCIT.RC);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCQCNCI.RC);
                BPCQCNCI.FLG = 'Y';
                Z_RET();
                if (pgmRtn) return;
            }
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCIQCIT.OUTPUT_DAT);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCQCNCI.OUTPUT_CITY_DAT);
            BPCQCNCI.OUTPUT_CAL_CODE = BPCQCNCI.OUTPUT_CITY_DAT.I_CAL_CODE;
        }
    }
    public void S000_CALL_BPZIQCNT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_BP_I_INQ_CNTY, BPCIQCNT);
        if (BPCIQCNT.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCIQCNT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCQCNCI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZIQCIT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_BP_I_INQ_CITY, BPCIQCIT);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCQCNCI.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCQCNCI = ");
            CEP.TRC(SCCGWA, BPCQCNCI);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
