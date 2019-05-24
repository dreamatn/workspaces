package com.hisun.BP;

import com.hisun.CI.*;
import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPPHIS {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_UPD_FHIST = "BP-R-UPD-FHIST";
    String WS_ERR_MSG = " ";
    char WS_RECORD_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRFHIST BPRFHIST = new BPRFHIST();
    BPCUFHIS BPCUFHIS = new BPCUFHIS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGMSG SCCGMSG;
    SCCGAPV SCCGAPV;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    BPCPPHIS BPCPPHIS;
    public void MP(SCCGWA SCCGWA, BPCPPHIS BPCPPHIS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPPHIS = BPCPPHIS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPPHIS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
        SCCGMSG = (SCCGMSG) GWA_SC_AREA.MSG_AREA_PTR;
        IBS.init(SCCGWA, BPRFHIST);
        IBS.init(SCCGWA, BPCUFHIS);
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCPPHIS.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_MOD_FHIST();
        if (pgmRtn) return;
        Z_RET();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPPHIS.AC_DATE);
        CEP.TRC(SCCGWA, BPCPPHIS.JRNNO);
        CEP.TRC(SCCGWA, BPCPPHIS.SEQ);
        CEP.TRC(SCCGWA, BPCPPHIS.PRINT_FLG);
        if (BPCPPHIS.AC_DATE == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_DATE_ERR, BPCPPHIS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCPPHIS.JRNNO == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_DATE_ERR, BPCPPHIS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCPPHIS.SEQ == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_DATE_ERR, BPCPPHIS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCPPHIS.PRINT_FLG == ' ') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_DATE_ERR, BPCPPHIS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_MOD_FHIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFHIST);
        IBS.init(SCCGWA, BPCUFHIS);
        BPRFHIST.KEY.AC_DT = BPCPPHIS.AC_DATE;
        BPRFHIST.KEY.JRNNO = BPCPPHIS.JRNNO;
        BPCUFHIS.DATA.POINTER = BPRFHIST;
        BPCUFHIS.DATA.REC_LEN = 690;
        BPCUFHIS.DATA.FUNC = '3';
        S000_CALL_BPZUFHIS();
        if (pgmRtn) return;
        WS_RECORD_FLG = 'N';
        BPCUFHIS.DATA.FUNC = '4';
        S000_CALL_BPZUFHIS();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUFHIS.RC);
        while (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_END_OF_TABLE) 
            && WS_RECORD_FLG != 'Y' 
            && BPRFHIST.KEY.JRN_SEQ <= BPCPPHIS.SEQ) {
            CEP.TRC(SCCGWA, BPCPPHIS.SEQ);
            CEP.TRC(SCCGWA, BPRFHIST.KEY.JRN_SEQ);
            if (BPCPPHIS.SEQ == BPRFHIST.KEY.JRN_SEQ) {
                WS_RECORD_FLG = 'Y';
                BPRFHIST.PRINT_FLG = BPCPPHIS.PRINT_FLG;
                CEP.TRC(SCCGWA, BPRFHIST.PRINT_FLG);
                BPCUFHIS.DATA.FUNC = '2';
                S000_CALL_BPZUFHIS();
                if (pgmRtn) return;
            }
            BPCUFHIS.DATA.FUNC = '4';
            S000_CALL_BPZUFHIS();
            if (pgmRtn) return;
        }
        BPCUFHIS.DATA.FUNC = '5';
        S000_CALL_BPZUFHIS();
        if (pgmRtn) return;
        if (WS_RECORD_FLG == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOTFND, BPCPPHIS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZUFHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_UPD_FHIST, BPCUFHIS);
        CEP.TRC(SCCGWA, BPCUFHIS.RC);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCUFHIS.RC);
        if (BPCUFHIS.RC.RC_CODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_END_OF_TABLE)) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUFHIS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPPHIS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCPPHIS.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCPPHIS = ");
            CEP.TRC(SCCGWA, BPCPPHIS);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
