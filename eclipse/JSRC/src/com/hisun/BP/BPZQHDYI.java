package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCKDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGWA;

public class BPZQHDYI {
    boolean pgmRtn = false;
    String K_PARM_CALEN = "CALEN";
    String K_SPE_CALENDAR = "*****";
    String K_PGM_NAME = "BPZQHDYI";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String CPN_CALL_BPZPRMM = "BP-PARM-MAINTAIN    ";
    String CPN_CALL_BPZITHOL = "BP-I-INQ-THOL       ";
    String CPN_CALL_BPZPTRNH = "BP-P-SPE-CAL-TRAN   ";
    String CPN_CALL_BPZPCKWD = "BP-P-CHK-WORK-DAY   ";
    String CPN_CALL_BPZPQPCD = "BP-P-INQ-PC         ";
    String CPN_CALN_READ = "BP-R-BRW-CALN       ";
    String WS_ERR_MSG = " ";
    int WS_EFF_DT = 0;
    int WS_EXP_DT = 0;
    short WS_I = 0;
    short WS_WEEK_NO = 0;
    BPZQHDYI_WS_CALEN_KEY WS_CALEN_KEY = new BPZQHDYI_WS_CALEN_KEY();
    char WS_STOP_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCSCALE BPCSCALE = new BPCSCALE();
    BPCOCKWD BPCOCKWD = new BPCOCKWD();
    BPCITHOL BPCITHOL = new BPCITHOL();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    BPCRCALN BPCRCALN = new BPCRCALN();
    BPRCALN BPRCALN = new BPRCALN();
    SCCGWA SCCGWA;
    BPCQHDYI BPCQHDYI;
    public void MP(SCCGWA SCCGWA, BPCQHDYI BPCQHDYI) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCQHDYI = BPCQHDYI;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZQHDYI return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSCALE);
        IBS.init(SCCGWA, BPCSCALE);
        IBS.init(SCCGWA, BPCOCKWD);
        IBS.init(SCCGWA, BPCITHOL);
        IBS.init(SCCGWA, BPCOQPCD);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_QUERY_CAL_INFO();
        if (pgmRtn) return;
        B030_QUERY_CNTY_LEGAL_HOLIDY();
        if (pgmRtn) return;
        B040_QUERY_SPECIL_HOLIDAY_INFO();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCQHDYI.INPUT_DAT.CAL_CODE.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERR, BPCQHDYI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCQHDYI.INPUT_DAT.DATE == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERR, BPCQHDYI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_QUERY_CAL_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCALN);
        IBS.init(SCCGWA, BPCRCALN);
        BPCRCALN.INFO.FUNC = 'R';
        BPRCALN.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        BPRCALN.KEY.CODE = BPCQHDYI.INPUT_DAT.CAL_CODE;
        JIBS_tmp_str[0] = "" + BPCQHDYI.INPUT_DAT.DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 4).trim().length() == 0) BPRCALN.KEY.YEAR = 0;
        else BPRCALN.KEY.YEAR = Short.parseShort(JIBS_tmp_str[0].substring(0, 4));
        CEP.TRC(SCCGWA, BPRCALN.KEY);
        BPRCALN.EFF_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCRCALN.INFO.POINTER = BPRCALN;
        BPCRCALN.INFO.LEN = 173;
        S000_CALL_BPZRCALN();
        if (pgmRtn) return;
