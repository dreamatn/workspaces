package com.hisun.DD;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCMSG_ERROR_MSG;
import com.hisun.BP.BPCPNHIS;
import com.hisun.BP.BPCPRMM;
import com.hisun.BP.BPCRMBPM;
import com.hisun.BP.BPRPARM;
import com.hisun.BP.BPRPRMT;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class DDZSMRAT {
    boolean pgmRtn = false;
    String K_PRDPR_TYPE = "PRDPR";
    String K_PDRAT_TYPE = "PDRAT";
    String K_OUTPUT_FMT = "DD503";
    String K_INQ_FMT_CD = "DDX01";
    String K_HIS_RMKS = "RATE PARM MAINTENANCE";
    String K_HIS_CPB_NM = "DDCHMSTR";
    String WS_ERR_MSG = " ";
    short WS_CNT = 0;
    short WS_IDX = 0;
    char WS_FOUND_FLG = ' ';
    DDZSMRAT_WS_PRD_RAT_INF WS_PRD_RAT_INF = new DDZSMRAT_WS_PRD_RAT_INF();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    DDCOMRAT DDCOMRAT = new DDCOMRAT();
    DDCHMSTR DDCMSTRO = new DDCHMSTR();
    DDCHMSTR DDCMSTRN = new DDCHMSTR();
    DDVMRAT DDVMRAT = new DDVMRAT();
    DDVMPRD DDVMPRD = new DDVMPRD();
    DDCUPARM DDCUPARM = new DDCUPARM();
    BPRPARM BPRPARM = new BPRPARM();
    BPCRMBPM BPCRMBPM = new BPCRMBPM();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    DDCUPRAT DDCUPRAT = new DDCUPRAT();
    SCCGWA SCCGWA;
    DDCSMRAT DDCSSMRAT;
    public void MP(SCCGWA SCCGWA, DDCSMRAT DDCSSMRAT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSSMRAT = DDCSSMRAT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSMRAT return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        WS_PRD_RAT_INF.WS_MRAT_KEY.WS_MPRD_KEY.WS_ACBR = 999999;
        if (DDCSSMRAT.FUNC == 'I'
            || DDCSSMRAT.FUNC == 'Q') {
            B010_CHECK_INPUT_DATA();
            if (pgmRtn) return;
            B020_QUERY_PROCESS_DDTPRAT();
            if (pgmRtn) return;
            B070_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (DDCSSMRAT.FUNC == 'A') {
            B010_CHECK_INPUT_DATA();
            if (pgmRtn) return;
            B030_CREATE_PROCESS_DDTPRAT();
            if (pgmRtn) return;
            B060_WRITE_HIS_PROC();
            if (pgmRtn) return;
            B070_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (DDCSSMRAT.FUNC == 'M') {
            B010_CHECK_INPUT_DATA();
            if (pgmRtn) return;
            B040_MODIFY_PROCESS_DDTPRAT();
            if (pgmRtn) return;
            B060_WRITE_HIS_PROC();
            if (pgmRtn) return;
            B070_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (DDCSSMRAT.FUNC == 'D') {
            B010_CHECK_INPUT_DATA();
            if (pgmRtn) return;
            B050_DELETE_PROCESS();
            if (pgmRtn) return;
            B060_WRITE_HIS_PROC();
            if (pgmRtn) return;
            B070_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (DDCSSMRAT.FUNC == 'B') {
            B010_CHECK_INPUT_DATA();
            if (pgmRtn) return;
            B090_BRW_PRD_RAT_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + DDCSSMRAT.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
    }
    public void B020_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPRMT);
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, DDVMRAT);
        BPRPRMT.KEY.TYP = K_PDRAT_TYPE;
        WS_PRD_RAT_INF.WS_MRAT_KEY.WS_MPRD_KEY.WS_PARM_CODE = DDCSSMRAT.KEY.PARM_CODE;
        WS_PRD_RAT_INF.WS_MRAT_KEY.WS_CCY = DDCSSMRAT.KEY.CCY;
        BPRPRMT.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_PRD_RAT_INF.WS_MRAT_KEY);
        BPCPRMM.EFF_DT = DDCSSMRAT.EFF_DATE;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDVMRAT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRPRMT.DAT_TXT);
        BPCPRMM.FUNC = '3';
        BPCPRMM.DAT_PTR = BPRPRMT;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPRMM.EFF_DT);
        CEP.TRC(SCCGWA, BPCPRMM.EXP_DT);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MRAT_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
