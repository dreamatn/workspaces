package com.hisun.AI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class AIOT5501 {
    DBParm AITGRVS_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT_9 = "AI501";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_GRVS_FLG = ' ';
    AIOT5501_WS_OUTPUT_DATA WS_OUTPUT_DATA = new AIOT5501_WS_OUTPUT_DATA();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    AIRGRVS AIRGRVS = new AIRGRVS();
    AIRGINF AIRGINF = new AIRGINF();
    AICRGINF AICRGINF = new AICRGINF();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    AIB5500_AWA_5500 AIB5500_AWA_5500;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIOT5501 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "AIB5500_AWA_5500>");
        AIB5500_AWA_5500 = (AIB5500_AWA_5500) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_BRW_REC_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AIB5500_AWA_5500.RVS_NO);
        if (AIB5500_AWA_5500.RVS_NO.trim().length() == 0) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_RVSNO_MUST_IPT;
            WS_FLD_NO = AIB5500_AWA_5500.RVS_NO_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_BRW_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AIRGINF);
        IBS.init(SCCGWA, AICRGINF);
        AICRGINF.INFO.FUNC = 'Q';
        AIRGINF.KEY.RVS_NO = AIB5500_AWA_5500.RVS_NO;
        S000_CALL_AIZRGINF();
        if (pgmRtn) return;
        if (AICRGINF.RETURN_INFO == 'N') {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_GINF_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, WS_OUTPUT_DATA);
        WS_OUTPUT_DATA.WS_OUT_GBAL = AIRGINF.G_BAL;
        IBS.init(SCCGWA, AIRGRVS);
        WS_GRVS_FLG = ' ';
        AIRGRVS.KEY.RVS_NO = AIB5500_AWA_5500.RVS_NO;
        T000_READ_AITGRVS();
        if (pgmRtn) return;
        WS_OUTPUT_DATA.WS_OUT_BRAC = AIRGRVS.AC;
        WS_OUTPUT_DATA.WS_OUT_NO = AIRGRVS.THEIR_AC;
        WS_OUTPUT_DATA.WS_OUT_NM = AIRGRVS.PAY_MAN;
        WS_OUTPUT_DATA.WS_OUT_PART = AIRGRVS.PART;
        WS_OUTPUT_DATA.WS_ITM = AIRGRVS.ITM;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_9;
        SCCFMT.DATA_PTR = WS_OUTPUT_DATA;
        SCCFMT.DATA_LEN = 460;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_AITGRVS() throws IOException,SQLException,Exception {
        AITGRVS_RD = new DBParm();
        AITGRVS_RD.TableName = "AITGRVS";
        AITGRVS_RD.where = "RVS_NO = :AIRGRVS.KEY.RVS_NO "
            + "AND STS = 'N'";
        AITGRVS_RD.fst = true;
        IBS.READ(SCCGWA, AIRGRVS, this, AITGRVS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_GRVS_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_GRVS_FLG = 'N';
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_GRVS_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITGRVS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZRGINF() throws IOException,SQLException,Exception {
        AICRGINF.INFO.POINTER = AIRGINF;
        AICRGINF.INFO.LEN = 242;
        IBS.CALLCPN(SCCGWA, "AI-R-MAIN-GINF", AICRGINF);
        if (AICRGINF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICRGINF.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
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
