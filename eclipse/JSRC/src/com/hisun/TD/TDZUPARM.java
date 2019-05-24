package com.hisun.TD;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCMSG_ERROR_MSG;
import com.hisun.BP.BPCPRMM;
import com.hisun.BP.BPRPRMT;
import com.hisun.DD.DDCMSG_ERROR_MSG;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class TDZUPARM {
    boolean pgmRtn = false;
    String K_PRDP_TYP = "TDPRM";
    String K_PRD_FMT = "TD900";
    String WS_MSGID = " ";
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    SCCFMT SCCFMT = new SCCFMT();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    SCCGWA SCCGWA;
    TDCUPARM TDCUPARM;
    public void MP(SCCGWA SCCGWA, TDCUPARM TDCUPARM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCUPARM = TDCUPARM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TDZUPARM return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (TDCUPARM.FUNC == 'I') {
            B030_INQUIRE_TDPARM();
            if (pgmRtn) return;
        } else if (TDCUPARM.FUNC == 'M') {
            B050_MODIFY_TDPARM();
            if (pgmRtn) return;
        } else {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_INVALID_OPT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        B070_OUTPUT_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCUPARM.FUNC);
        if (TDCUPARM.FUNC == ' ') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_INVALID_OPT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_INQUIRE_TDPARM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, BPRPRMT);
        BPCPRMM.FUNC = '3';
        BPRPRMT.KEY.TYP = K_PRDP_TYP;
        BPRPRMT.KEY.CD = SCCGWA.COMM_AREA.TR_BANK;
        CEP.TRC(SCCGWA, BPRPRMT.KEY.TYP);
        CEP.TRC(SCCGWA, BPRPRMT.KEY.CD);
        BPCPRMM.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPRMM.DAT_PTR = BPRPRMT;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRPRMT.DAT_TXT);
        CEP.TRC(SCCGWA, TDCUPARM.PARM_DATA.BTZ_DAYS);
        CEP.TRC(SCCGWA, TDCUPARM.PARM_DATA.YBT_LINE_LMT);
        CEP.TRC(SCCGWA, TDCUPARM.PARM_DATA.RESET_PWD_EFF_DAYS);
        CEP.TRC(SCCGWA, TDCUPARM.PARM_DATA.LOST_EXP_DAY);
        CEP.TRC(SCCGWA, TDCUPARM.PARM_DATA.CAN_LOST_DAYS);
        CEP.TRC(SCCGWA, TDCUPARM.PARM_DATA.LOST_NEWUSE_DAYS);
        CEP.TRC(SCCGWA, TDCUPARM.PARM_DATA.TICKET_TERM);
        CEP.TRC(SCCGWA, TDCUPARM.PARM_DATA.COM_LNDT);
    }
    public void B050_MODIFY_TDPARM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCUPARM.PARM_DATA.BTZ_DAYS);
        CEP.TRC(SCCGWA, TDCUPARM.PARM_DATA.YBT_LINE_LMT);
        CEP.TRC(SCCGWA, TDCUPARM.PARM_DATA.RESET_PWD_EFF_DAYS);
        CEP.TRC(SCCGWA, TDCUPARM.PARM_DATA.LOST_EXP_DAY);
        CEP.TRC(SCCGWA, TDCUPARM.PARM_DATA.CAN_LOST_DAYS);
        CEP.TRC(SCCGWA, TDCUPARM.PARM_DATA.LOST_NEWUSE_DAYS);
        CEP.TRC(SCCGWA, TDCUPARM.PARM_DATA.TICKET_TERM);
        CEP.TRC(SCCGWA, TDCUPARM.PARM_DATA.COM_LNDT);
        IBS.init(SCCGWA, BPRPRMT);
        IBS.init(SCCGWA, BPCPRMM);
        BPRPRMT.KEY.TYP = K_PRDP_TYP;
        BPRPRMT.KEY.CD = SCCGWA.COMM_AREA.TR_BANK;
        BPCPRMM.DAT_PTR = BPRPRMT;
        BPCPRMM.FUNC = '4';
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        BPCPRMM.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCUPARM.PARM_DATA);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRPRMT.DAT_TXT);
        BPCPRMM.FUNC = '2';
        BPCPRMM.DAT_PTR = BPRPRMT;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_IRPRD_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
