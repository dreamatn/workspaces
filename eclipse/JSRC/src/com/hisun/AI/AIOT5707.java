package com.hisun.AI;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class AIOT5707 {
    DBParm AITCMIB_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int K_PPMP_BR = "666666";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    AIOT5707_WS_OUTPUT WS_OUTPUT = new AIOT5707_WS_OUTPUT();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    AICSCMIB AICSCMIB = new AICSCMIB();
    BPCPQORG BPCPQORG = new BPCPQORG();
    AIRCMIB AIRCMIB = new AIRCMIB();
    AICPQITM AICPQITM = new AICPQITM();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    AIB5700_AWA_5700 AIB5700_AWA_5700;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIOT5707 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "AIB5700_AWA_5700>");
        AIB5700_AWA_5700 = (AIB5700_AWA_5700) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_QUE_REC_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AIB5700_AWA_5700.ITM);
        if (AIB5700_AWA_5700.ITM.trim().length() > 0) {
            IBS.init(SCCGWA, AICPQITM);
            AICPQITM.INPUT_DATA.BOOK_FLG = AIB5700_AWA_5700.GL_BOOK;
            AICPQITM.INPUT_DATA.NO = AIB5700_AWA_5700.ITM;
            S00_CALL_AIZPQITM();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, AIB5700_AWA_5700.BR);
    }
    public void B020_QUE_REC_PROC() throws IOException,SQLException,Exception {
        if (AIB5700_AWA_5700.BR != K_PPMP_BR) {
            IBS.init(SCCGWA, AICSCMIB);
            AICSCMIB.FUNC = 'Q';
            AICSCMIB.GL_BOOK = AIB5700_AWA_5700.GL_BOOK;
            AICSCMIB.ITM = AIB5700_AWA_5700.ITM;
            AICSCMIB.BR = AIB5700_AWA_5700.BR;
            AICSCMIB.SEQ = AIB5700_AWA_5700.SEQ;
            AICSCMIB.FUNC_N = AIB5700_AWA_5700.FUNC;
            CEP.TRC(SCCGWA, AIB5700_AWA_5700.GL_BOOK);
            CEP.TRC(SCCGWA, AIB5700_AWA_5700.ITM);
            CEP.TRC(SCCGWA, AIB5700_AWA_5700.BR);
            CEP.TRC(SCCGWA, AIB5700_AWA_5700.SEQ);
            CEP.TRC(SCCGWA, AIB5700_AWA_5700.FUNC);
            S00_CALL_AIZSCMIB();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "INQUIRE AITCMIB FOR PPMP");
            IBS.init(SCCGWA, AIRCMIB);
            AIRCMIB.KEY.GL_BOOK = AIB5700_AWA_5700.GL_BOOK;
            AIRCMIB.KEY.ITM = AIB5700_AWA_5700.ITM;
            AIRCMIB.KEY.SEQ = AIB5700_AWA_5700.SEQ;
            T000_READ_AITCMIB();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                R000_DATA_OUTPUT();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUTPUT);
        WS_OUTPUT.WS_OUT_GL_BOOK = AIRCMIB.KEY.GL_BOOK;
        WS_OUTPUT.WS_OUT_BR = AIRCMIB.KEY.BR;
        WS_OUTPUT.WS_OUT_ITM = AIRCMIB.KEY.ITM;
        WS_OUTPUT.WS_OUT_SEQ = AIRCMIB.KEY.SEQ;
        WS_OUTPUT.WS_OUT_NAME = AIRCMIB.CHS_NM;
        WS_OUTPUT.WS_OUT_AC_TYP = AIRCMIB.AC_TYP;
        WS_OUTPUT.WS_OUT_CCY_LMT = AIRCMIB.CCY_LMT;
        WS_OUTPUT.WS_OUT_BAL_DIR = AIRCMIB.BAL_DIR;
        WS_OUTPUT.WS_OUT_BAL_RFLG = AIRCMIB.BAL_RFLG;
        WS_OUTPUT.WS_OUT_DTL_FLG = AIRCMIB.DTL_FLG;
        WS_OUTPUT.WS_OUT_RVS_TYP = AIRCMIB.RVS_TYP;
        WS_OUTPUT.WS_OUT_RVS_KND = AIRCMIB.RVS_KND;
        WS_OUTPUT.WS_OUT_RVS_EXP = AIRCMIB.RVS_EXP;
        WS_OUTPUT.WS_OUT_RVS_UNIT = AIRCMIB.RVS_UNIT;
        WS_OUTPUT.WS_OUT_AC_EXP = AIRCMIB.AC_EXP;
        WS_OUTPUT.WS_OUT_MANUAL = AIRCMIB.MANUAL_FLG;
        WS_OUTPUT.WS_OUT_AMT_DIR = AIRCMIB.AMT_DIR;
        WS_OUTPUT.WS_OUT_ONL_FLG = AIRCMIB.ONL_FLG;
        WS_OUTPUT.WS_OUT_CARD_FLG = AIRCMIB.CARD_FLG;
        WS_OUTPUT.WS_OUT_HOT_FLG = AIRCMIB.HOT_FLG;
        WS_OUTPUT.WS_OUT_DRLT_BAL = AIRCMIB.DRLT_BAL;
        WS_OUTPUT.WS_OUT_CRLT_BAL = AIRCMIB.CRLT_BAL;
        WS_OUTPUT.WS_OUT_BAL_CHK = AIRCMIB.BAL_CHK;
        WS_OUTPUT.WS_OUT_EFF_DATE = AIRCMIB.EFF_DATE;
        WS_OUTPUT.WS_OUT_EXP_DATE = AIRCMIB.EXP_DATE;
        WS_OUTPUT.WS_OUT_APP1 = AIRCMIB.APP1;
        WS_OUTPUT.WS_OUT_APP2 = AIRCMIB.APP2;
        WS_OUTPUT.WS_OUT_APP3 = AIRCMIB.APP3;
        WS_OUTPUT.WS_OUT_APP4 = AIRCMIB.APP4;
        WS_OUTPUT.WS_OUT_APP5 = AIRCMIB.APP5;
        WS_OUTPUT.WS_OUT_APP6 = AIRCMIB.APP6;
        WS_OUTPUT.WS_OUT_APP7 = AIRCMIB.APP7;
        WS_OUTPUT.WS_OUT_APP8 = AIRCMIB.APP8;
        WS_OUTPUT.WS_OUT_APP9 = AIRCMIB.APP9;
        WS_OUTPUT.WS_OUT_APP10 = AIRCMIB.APP10;
        WS_OUTPUT.WS_OUT_STS = AIRCMIB.STS;
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_OUT_EFF_DATE);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_OUT_EXP_DATE);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_OUT_APP1);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_OUT_APP2);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_OUT_APP3);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_OUT_APP4);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_OUT_APP5);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "AI702";
        SCCFMT.DATA_PTR = WS_OUTPUT;
        SCCFMT.DATA_LEN = 296;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_AITCMIB() throws IOException,SQLException,Exception {
        AITCMIB_RD = new DBParm();
        AITCMIB_RD.TableName = "AITCMIB";
        AITCMIB_RD.where = "GL_BOOK = :AIRCMIB.KEY.GL_BOOK "
            + "AND ITM = :AIRCMIB.KEY.ITM "
            + "AND SEQ = :AIRCMIB.KEY.SEQ";
        AITCMIB_RD.fst = true;
        IBS.READ(SCCGWA, AIRCMIB, this, AITCMIB_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_READ_AITCMIB_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
        }
    }
    public void S00_CALL_AIZSCMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-S-MAIN-CMIB", AICSCMIB);
    }
    public void S00_CALL_AIZPQITM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-ITM", AICPQITM);
        CEP.TRC(SCCGWA, AICPQITM.RC);
        if (AICPQITM.RC.RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICPQITM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S00_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        CEP.TRC(SCCGWA, BPCPQORG.RC.RC_CODE);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
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
