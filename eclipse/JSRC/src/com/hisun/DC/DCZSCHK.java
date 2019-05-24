package com.hisun.DC;

import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSCHK {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "DC850";
    String WS_ERR_MSG = " ";
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    DCCUCHK DCCUCHK = new DCCUCHK();
    DCCOCHK DCCOCHK = new DCCOCHK();
    SCCBINF SCCBINF = new SCCBINF();
    SCCGWA SCCGWA;
    DCCSCHK DCCSCHK;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, DCCSCHK DCCSCHK) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSCHK = DCCSCHK;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSCHK return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_PROC();
        if (pgmRtn) return;
        B020_AC_CHECK_PROC();
        if (pgmRtn) return;
        B030_OUTPUT_DATA_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSCHK.DATA.OPT_TYP);
        CEP.TRC(SCCGWA, DCCSCHK.DATA.SPR_TYP);
        CEP.TRC(SCCGWA, DCCSCHK.DATA.AC);
        CEP.TRC(SCCGWA, DCCSCHK.DATA.SEQ);
        if (DCCSCHK.DATA.OPT_TYP == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_OPT_TYP_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSCHK.DATA.SPR_TYP == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_SPR_TYP_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSCHK.DATA.AC.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_AC_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSCHK.DATA.OPT_TYP == '5' 
            && DCCSCHK.DATA.HLD_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLDNO_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_AC_CHECK_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUCHK);
        DCCUCHK.DATA.OPT_TYP = DCCSCHK.DATA.OPT_TYP;
        DCCUCHK.DATA.SPR_TYP = DCCSCHK.DATA.SPR_TYP;
        DCCUCHK.DATA.AC = DCCSCHK.DATA.AC;
        DCCUCHK.DATA.SEQ = DCCSCHK.DATA.SEQ;
        DCCUCHK.DATA.HLD_NO = DCCSCHK.DATA.HLD_NO;
        DCCUCHK.DATA.FUNC = 'I';
        CEP.TRC(SCCGWA, DCCUCHK.DATA.FUNC);
        S000_CALL_DCZUCHK();
        if (pgmRtn) return;
    }
    public void B030_OUTPUT_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCOCHK);
        DCCOCHK.DATA.OPT_TYP = DCCUCHK.DATA.OPT_TYP;
        DCCOCHK.DATA.SPR_TYP = DCCUCHK.DATA.SPR_TYP;
        DCCOCHK.DATA.AC = DCCUCHK.DATA.AC;
        DCCOCHK.DATA.SEQ = DCCUCHK.DATA.SEQ;
        DCCOCHK.DATA.HLD_NO = DCCUCHK.DATA.HLD_NO;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DCCOCHK;
        SCCFMT.DATA_LEN = 60;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_DCZUCHK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-UNIT-CHK", DCCUCHK);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
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
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
