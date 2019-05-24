package com.hisun.DD;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCMSG_ERROR_MSG;
import com.hisun.BP.BPCPNHIS;
import com.hisun.BP.BPCPQPRD;
import com.hisun.BP.BPCPRMM;
import com.hisun.BP.BPRPRMT;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class DDZSMPRD {
    boolean pgmRtn = false;
    String K_PRDPR_TYPE = "PRDPR";
    String K_PDRAT_TYPE = "PDRAT";
    String K_OUTPUT_FMT = "DD501";
    String K_OUTPUT_FMT1 = "DDX01";
    String K_HIS_CPB_NM = "DDCHMPRD";
    String K_HIS_RMKS = "DDPRD PARM MAINTENANCE";
    String WS_ERR_MSG = " ";
    short WS_CNT = 0;
    String WS_INFO = " ";
    DDZSMPRD_WS_MRAT_CODE WS_MRAT_CODE = new DDZSMPRD_WS_MRAT_CODE();
    DDZSMPRD_WS_PRM_KEY WS_PRM_KEY = new DDZSMPRD_WS_PRM_KEY();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    DDCHMPRD DDCMPRDO = new DDCHMPRD();
    DDCHMPRD DDCMPRDN = new DDCHMPRD();
    DDCOMPRD DDCOMPRD = new DDCOMPRD();
    DDVMPRD DDVMPRD = new DDVMPRD();
    DDVMRAT DDVMRAT = new DDVMRAT();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    DDCUPARM DDCUPARM = new DDCUPARM();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    DDCUPRAT DDCUPRAT = new DDCUPRAT();
    DDRPRDD DDRPRDD = new DDRPRDD();
    SCCGWA SCCGWA;
    DDCSMPRD DDCSSMPRD;
    public void MP(SCCGWA SCCGWA, DDCSMPRD DDCSSMPRD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSSMPRD = DDCSSMPRD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSMPRD return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        WS_PRM_KEY.WS_ACBR = 999999999;
        WS_PRM_KEY.WS_PRM_CD = DDCSSMPRD.KEY.PARM_CODE;
        if (DDCSSMPRD.FUNC == 'Q'
            || DDCSSMPRD.FUNC == 'I') {
            B010_CHECK_INPUT_DATA();
            if (pgmRtn) return;
            B030_QUERY_PROCESS();
            if (pgmRtn) return;
            B130_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (DDCSSMPRD.FUNC == 'A') {
            B010_CHECK_INPUT_DATA();
            if (pgmRtn) return;
            B050_CREATE_PROCESS();
            if (pgmRtn) return;
            B110_WRITE_HIS_PROC();
            if (pgmRtn) return;
            B130_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (DDCSSMPRD.FUNC == 'M') {
            B010_CHECK_INPUT_DATA();
            if (pgmRtn) return;
            B070_MODIFY_PROCESS();
            if (pgmRtn) return;
            B110_WRITE_HIS_PROC();
            if (pgmRtn) return;
            B130_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (DDCSSMPRD.FUNC == 'D') {
            B010_CHECK_INPUT_DATA();
            if (pgmRtn) return;
            B090_DELETE_PROCESS();
            if (pgmRtn) return;
            B110_WRITE_HIS_PROC();
            if (pgmRtn) return;
            B130_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + DDCSSMPRD.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSSMPRD.KEY.PARM_CODE);
        CEP.TRC(SCCGWA, DDCSSMPRD.EFF_DATE);
        CEP.TRC(SCCGWA, DDCSSMPRD.EXP_DATE);
        CEP.TRC(SCCGWA, DDCSSMPRD.VAL.CHN_NAME);
        CEP.TRC(SCCGWA, DDCSSMPRD.VAL.REMARK);
        CEP.TRC(SCCGWA, DDCSSMPRD.VAL.CUST_TYPE);
        CEP.TRC(SCCGWA, DDCSSMPRD.VAL.PRD_TOOL_PSB);
        CEP.TRC(SCCGWA, DDCSSMPRD.VAL.PRD_TOOL_CARD);
        CEP.TRC(SCCGWA, DDCSSMPRD.VAL.PRD_TOOL_CHQ);
        CEP.TRC(SCCGWA, DDCSSMPRD.VAL.CUR_TYPE);
        CEP.TRC(SCCGWA, DDCSSMPRD.VAL.CCY[1-1]);
        CEP.TRC(SCCGWA, DDCSSMPRD.VAL.CCY[2-1]);
        CEP.TRC(SCCGWA, DDCSSMPRD.VAL.CCY[3-1]);
        CEP.TRC(SCCGWA, DDCSSMPRD.VAL.CCY[4-1]);
        CEP.TRC(SCCGWA, DDCSSMPRD.VAL.CCY[5-1]);
        CEP.TRC(SCCGWA, DDCSSMPRD.VAL.CCY[6-1]);
        CEP.TRC(SCCGWA, DDCSSMPRD.VAL.CCY[7-1]);
        CEP.TRC(SCCGWA, DDCSSMPRD.VAL.CCY[8-1]);
        CEP.TRC(SCCGWA, DDCSSMPRD.VAL.CCY[9-1]);
        CEP.TRC(SCCGWA, DDCSSMPRD.VAL.CCY[10-1]);
        CEP.TRC(SCCGWA, DDCSSMPRD.VAL.CCY[11-1]);
        CEP.TRC(SCCGWA, DDCSSMPRD.VAL.CCY[12-1]);
        CEP.TRC(SCCGWA, DDCSSMPRD.VAL.OVERDRAFT_FAC);
        CEP.TRC(SCCGWA, DDCSSMPRD.VAL.CASH_TXN_TYPE);
        CEP.TRC(SCCGWA, DDCSSMPRD.VAL.CASH_MSG_TYPE);
        CEP.TRC(SCCGWA, DDCSSMPRD.VAL.CROS_DR_LMT);
        CEP.TRC(SCCGWA, DDCSSMPRD.VAL.CROS_CR_LMT);
        CEP.TRC(SCCGWA, DDCSSMPRD.VAL.CAL_DINT_METH);
        CEP.TRC(SCCGWA, DDCSSMPRD.VAL.TAX_RATE_BASE);
        CEP.TRC(SCCGWA, DDCSSMPRD.VAL.TAX_RATE_TENOR);
        CEP.TRC(SCCGWA, DDCSSMPRD.VAL.DEP_POST_PERIOD1);
        CEP.TRC(SCCGWA, DDCSSMPRD.VAL.DEP_POST_DATE1);
        CEP.TRC(SCCGWA, DDCSSMPRD.VAL.DEP_POST_PERIOD2);
        CEP.TRC(SCCGWA, DDCSSMPRD.VAL.DEP_POST_DATE2);
        CEP.TRC(SCCGWA, DDCSSMPRD.VAL.OD_POST_PERIOD1);
        CEP.TRC(SCCGWA, DDCSSMPRD.VAL.OD_POST_DATE1);
        CEP.TRC(SCCGWA, DDCSSMPRD.VAL.OD_POST_PERIOD2);
        CEP.TRC(SCCGWA, DDCSSMPRD.VAL.OD_POST_DATE2);
        CEP.TRC(SCCGWA, DDCSSMPRD.VAL.CERT_FLG);
        CEP.TRC(SCCGWA, DDCSSMPRD.VAL.AUFR_FLG);
        CEP.TRC(SCCGWA, DDCSSMPRD.VAL.TD_PROD);
        CEP.TRC(SCCGWA, DDCSSMPRD.VAL.TD_FLG);
        CEP.TRC(SCCGWA, DDCSSMPRD.VAL.GM_FLG);
    }
    public void B030_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDVMPRD);
        IBS.init(SCCGWA, DDCUPARM);
        DDCUPARM.TX_TYPE = 'I';
        DDCUPARM.DATA.KEY.PRDMO_CD = "CAAC";
        DDCUPARM.DATA.KEY.PARM_CODE = DDCSSMPRD.KEY.PARM_CODE;
        DDCUPARM.DATE.EFF_DATE = DDCSSMPRD.EFF_DATE;
        S000_CALL_DDZUPARM();
        if (pgmRtn) return;
        if (DDCUPARM.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, DDCUPARM.RC);
        } else {
            CEP.TRC(SCCGWA, "--DDTMPRD RECORD FOUND");
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDCUPARM.DATA);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DDVMPRD);
        }
        CEP.TRC(SCCGWA, DDVMPRD);
    }
    public void B050_CREATE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDVMPRD);
        IBS.init(SCCGWA, DDCUPARM);
        R000_TRANS_COMM_DATA_TO_VIEW();
        if (pgmRtn) return;
        DDCUPARM.TX_TYPE = 'A';
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDVMPRD);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DDCUPARM.DATA);
        DDCUPARM.DATA.KEY.PRDMO_CD = "CAAC";
        DDCUPARM.DATE.EFF_DATE = DDCSSMPRD.EFF_DATE;
        DDCUPARM.DATE.EXP_DATE = DDCSSMPRD.EXP_DATE;
        S000_CALL_DDZUPARM();
        if (pgmRtn) return;
        if (DDCUPARM.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, DDCUPARM.RC);
        } else {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDCUPARM.DATA);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DDVMPRD);
        }
        CEP.TRC(SCCGWA, DDVMPRD);
    }
    public void B070_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDVMPRD);
        IBS.init(SCCGWA, DDCUPARM);
        DDCUPARM.TX_TYPE = 'I';
        DDCUPARM.DATA.KEY.PRDMO_CD = "CAAC";
        DDCUPARM.DATA.KEY.PARM_CODE = DDCSSMPRD.KEY.PARM_CODE;
        DDCUPARM.DATE.EFF_DATE = DDCSSMPRD.EFF_DATE;
        S000_CALL_DDZUPARM();
        if (pgmRtn) return;
        if (DDCUPARM.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, DDCUPARM.RC);
        } else {
            CEP.TRC(SCCGWA, DDCSSMPRD.VAL.CUST_TYPE);
            CEP.TRC(SCCGWA, DDCUPARM.DATA.VAL.CUST_TYPE);
            if (DDCSSMPRD.VAL.CUST_TYPE != DDCUPARM.DATA.VAL.CUST_TYPE) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_PROD_TYP_CANNOT_CHG;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDCUPARM.DATA);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DDVMPRD);
            CEP.TRC(SCCGWA, DDVMPRD);
        }
        R000_TRANS_HIS_DATA_OLD();
        if (pgmRtn) return;
        R000_TRANS_HIS_DATA_NEW();
        if (pgmRtn) return;
