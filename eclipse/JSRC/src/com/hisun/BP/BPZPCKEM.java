package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPCKEM {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    boolean pgmRtn = false;
    String CPN_CALN_READ = "BP-R-BRW-CALN       ";
    short WS_M = 0;
    short WS_D = 0;
    short WS_YEAR = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRCAL BPRCAL = new BPRCAL();
    BPCPARM BPCPARM = new BPCPARM();
    BPCRCALN BPCRCALN = new BPCRCALN();
    BPRCALN BPRCALN = new BPRCALN();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCOCKEM BPCOCKEM;
    public void MP(SCCGWA SCCGWA, BPCOCKEM BPCOCKEM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCOCKEM = BPCOCKEM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPCKEM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, BPCOCKEM.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_GET_END_OF_MONTH();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (BPCOCKEM.CAL_CODE.trim().length() == 0 
            || BPCOCKEM.YEAR <= 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCOCKEM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_GET_END_OF_MONTH() throws IOException,SQLException,Exception {
        for (WS_M = 1; WS_M <= 12; WS_M += 1) {
            BPCOCKEM.EOM_DATE[WS_M-1].EOM_YEAR = BPCOCKEM.YEAR;
            BPCOCKEM.EOM_DATE[WS_M-1].EOM_MON = WS_M;
            BPCOCKEM.EOM_DATE[WS_M-1].EOM_DAY = 0;
        }
        WS_YEAR = BPCOCKEM.YEAR;
        S000_READ_BPTCAL();
        if (pgmRtn) return;
        for (WS_M = 1; WS_M <= 12; WS_M += 1) {
            for (WS_D = 1; WS_D <= 31; WS_D += 1) {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRCAL.DATA_TEXT.DATA.MM[WS_M-1].DD[WS_D-1]);
                if (JIBS_tmp_str[0].equalsIgnoreCase("W")) {
                    BPCOCKEM.EOM_DATE[WS_M-1].EOM_DAY = WS_D;
                }
            }
        }
    }
    public void S000_READ_BPTCAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCALN);
        IBS.init(SCCGWA, BPCRCALN);
        IBS.init(SCCGWA, BPRCAL);
        BPCRCALN.INFO.FUNC = 'R';
        BPRCALN.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        BPRCALN.KEY.CODE = BPCOCKEM.CAL_CODE;
        BPRCALN.KEY.YEAR = WS_YEAR;
        CEP.TRC(SCCGWA, BPRCALN.KEY);
        R000_PARM_DATA_PROCESS();
        if (pgmRtn) return;
        if (BPCRCALN.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CAL_NOTFND, BPCOCKEM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPRCALN.DATA == null) BPRCALN.DATA = "";
        JIBS_tmp_int = BPRCALN.DATA.length();
        for (int i=0;i<386-JIBS_tmp_int;i++) BPRCALN.DATA += " ";
        IBS.CPY2CLS(SCCGWA, BPRCALN.DATA.substring(0, 372), BPRCAL.DATA_TEXT.DATA);
        BPRCAL.DATA_TEXT.DATA.CNTYS_CD = BPRCALN.CNTY_CODE;
        BPRCAL.DATA_TEXT.DATA.CITY_CD = BPRCALN.CITY_CODE;
    }
    public void R000_PARM_DATA_PROCESS() throws IOException,SQLException,Exception {
        BPRCALN.EFF_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCRCALN.INFO.POINTER = BPRCALN;
        BPCRCALN.INFO.LEN = 173;
        S000_CALL_BPZRCALN();
        if (pgmRtn) return;
        if (BPCRCALN.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CAL_NOTFND, BPCOCKEM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRCALN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_CALN_READ, BPCRCALN);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCOCKEM.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCOCKEM = ");
            CEP.TRC(SCCGWA, BPCOCKEM);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
