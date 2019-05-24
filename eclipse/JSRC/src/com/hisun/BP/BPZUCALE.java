package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCKDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class BPZUCALE {
    boolean pgmRtn = false;
    String K_CMP_CHK_CAL_CODE = "BP-P-CHK-CAL-CODE";
    String K_CMP_INQ_CAL_CODE = "BP-P-INQ-CAL-CODE";
    String K_CMP_CALL_BPZITHOL = "BP-I-INQ-THOL    ";
    String K_CMP_MAIN_BPTCALR = "BP-R-MAINT-CALR";
    String K_CMP_BP_PARM_MAINTAIN = "BP-PARM-MAINTAIN ";
    String K_CMP_BP_R_MBRW_PARM = "BP-R-MBRW-PARM   ";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String K_PARM_TYPE = "CALEN";
    String K_MONTH_NAME = "JANFEBMARAPRMAYJUNJULAUGSEPOCTNOVDEC";
    String CPN_CALN_READ = "BP-R-BRW-CALN       ";
    BPZUCALE_WS_RC WS_RC = new BPZUCALE_WS_RC();
    short WS_K = 0;
    short WS_I = 0;
    short WS_J = 0;
    short WS_II = 0;
    short WS_III = 0;
    short WS_IDX = 0;
    short WS_MON = 0;
    BPZUCALE_WS_GET_DAYS_DATA WS_GET_DAYS_DATA = new BPZUCALE_WS_GET_DAYS_DATA();
    String WS_CAL_CODE = " ";
    BPZUCALE_WS_CAL_KEY WS_CAL_KEY = new BPZUCALE_WS_CAL_KEY();
    BPZUCALE_WS_MONTH_INFO WS_MONTH_INFO = new BPZUCALE_WS_MONTH_INFO();
    char WS_DAY_TYP_FLG = ' ';
    char WS_STOP_FLG = ' ';
    char WS_AUTOGEN_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPCQ01 BPCQ01 = new BPCQ01();
    BPCOCCAL BPCOCCAL = new BPCOCCAL();
    BPCOQCAL BPCOQCAL = new BPCOQCAL();
    BPCTCALR BPCTCALR = new BPCTCALR();
    BPCHCAL BPCOHCAL = new BPCHCAL();
    BPCHCAL BPCNHCAL = new BPCHCAL();
    BPRCALR BPRCALR = new BPRCALR();
    BPCITHOL BPCITHOL = new BPCITHOL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCQCNCI BPCQCNCI = new BPCQCNCI();
    BPCRCALN BPCRCALN = new BPCRCALN();
    BPRCALN BPRCALN = new BPRCALN();
    SCCGWA SCCGWA;
    BPCUCALE BPCUCALE;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCUCALE BPCUCALE) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCUCALE = BPCUCALE;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZUCALE return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, WS_RC);
        IBS.init(SCCGWA, BPCOQCAL);
        WS_RC.WS_RC_MMO = "BP";
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        if (BPCUCALE.FUNC == 'A'
            || BPCUCALE.FUNC == 'U'
            || BPCUCALE.FUNC == 'C') {
            B210_KEY_PROCESS();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCUCALE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUCALE.UP_DATE);
        if (JIBS_tmp_str[0].equalsIgnoreCase("0") 
            || (BPCUCALE.CAL_DETAIL.CNTYS_CD.trim().length() == 0 
            && BPCUCALE.CAL_DETAIL.CITY_CD.trim().length() == 0)) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCUCALE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B210_KEY_PROCESS() throws IOException,SQLException,Exception {
        if (BPCUCALE.FUNC == 'A') {
            BPCRCALN.INFO.FUNC = 'C';
        } else if (BPCUCALE.FUNC == 'U') {
            BPCRCALN.INFO.FUNC = 'U';
        }
        if (BPCUCALE.FUNC == 'A') {
            CEP.TRC(SCCGWA, "ADD CALEN RECORD");
            R000_GET_HOLIDAY();
            if (pgmRtn) return;
            B230_INITIALIZE_PROCESS();
            if (pgmRtn) return;
            BPCRCALN.INFO.FUNC = 'C';
            BPRCALN.CDESC = BPCUCALE.CAL_NAME;
            BPRCALN.DATA = IBS.CLS2CPY(SCCGWA, BPCUCALE.CAL_DETAIL.CALENDAR);
            BPRCALN.CNTY_CODE = BPCUCALE.CAL_DETAIL.CNTYS_CD;
            BPRCALN.CITY_CODE = BPCUCALE.CAL_DETAIL.CITY_CD;
            CEP.TRC(SCCGWA, BPRCALN.KEY.YEAR);
            CEP.TRC(SCCGWA, BPRCALN.KEY.CODE);
            CEP.TRC(SCCGWA, BPRCALN.CDESC);
            CEP.TRC(SCCGWA, BPRCALN.EFF_DATE);
            CEP.TRC(SCCGWA, BPRCALN.EXP_DATE);
