package com.hisun.AI;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class AIOT5516 {
    brParm AITPMIB_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_S_BRW_HMIB = "AI-S-BRW-HMIB  ";
    String CPN_P_INQ_ORG_LOW = "BP-P-INQ-ORG-LOW";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_LAST_BR = 0;
    int WS_CNT = 0;
    int WS_CNT1 = 0;
    AIOT5516_WS_BRW_OUTPUT WS_BRW_OUTPUT = new AIOT5516_WS_BRW_OUTPUT();
    AIOT5516_WS_IA_AC WS_IA_AC = new AIOT5516_WS_IA_AC();
    AIOT5516_WS_SUM_AMT WS_SUM_AMT = new AIOT5516_WS_SUM_AMT();
    char WS_BR_FLG = ' ';
    char WS_PMIB_FLG = ' ';
    int WS_AC_DATE = 0;
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPCQCCY BPCQCCY = new BPCQCCY();
    AIRPMIB AIRPMIB = new AIRPMIB();
    BPCPORLO BPCPORLO = new BPCPORLO();
    BPCPQORG BPCPQORG = new BPCPQORG();
    AICPQMIB AICPQMIB = new AICPQMIB();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    AIB5510_AWA_5510 AIB5510_AWA_5510;
    BPCPORUP_DATA_INFO BPCPORUP;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        CEP.TRC(SCCGWA, "PROGRAM AIOT5516 PROCESS START!");
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "PROGRAM AIOT5516 END SUCCESSFULLY!");
        CEP.TRC(SCCGWA, "AIOT5516 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "AIB5510_AWA_5510>");
        AIB5510_AWA_5510 = (AIB5510_AWA_5510) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        IBS.init(SCCGWA, WS_SUM_AMT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_BRW_REC_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AIB5510_AWA_5510.GL_BOOK);
        CEP.TRC(SCCGWA, AIB5510_AWA_5510.BR);
        CEP.TRC(SCCGWA, AIB5510_AWA_5510.CCY);
        CEP.TRC(SCCGWA, AIB5510_AWA_5510.ITM);
        CEP.TRC(SCCGWA, AIB5510_AWA_5510.SEQ);
        CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.BR);
        if (AIB5510_AWA_5510.GL_BOOK.trim().length() == 0 
            || AIB5510_AWA_5510.BR == 0 
            || AIB5510_AWA_5510.CCY.trim().length() == 0 
            || AIB5510_AWA_5510.ITM.trim().length() == 0 
            || AIB5510_AWA_5510.SEQ == 0) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (AIB5510_AWA_5510.BR != 0 
            && AIB5510_AWA_5510.BR != 999999999) {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = AIB5510_AWA_5510.BR;
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPQORG.ATTR);
            if (BPCPQORG.ATTR != '2') {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_NOT_BOOK_BR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        WS_IA_AC.WS_IA_BR = AIB5510_AWA_5510.BR;
        WS_IA_AC.WS_IA_CCY = AIB5510_AWA_5510.CCY;
        WS_IA_AC.WS_IA_ITM = AIB5510_AWA_5510.ITM;
        WS_IA_AC.WS_IA_SEQ = AIB5510_AWA_5510.SEQ;
        CEP.TRC(SCCGWA, WS_IA_AC);
        IBS.init(SCCGWA, AICPQMIB);
        AICPQMIB.INPUT_DATA.GL_BOOK = AIB5510_AWA_5510.GL_BOOK;
        AICPQMIB.INPUT_DATA.AC = IBS.CLS2CPY(SCCGWA, WS_IA_AC);
        AICPQMIB.INPUT_DATA.CCY = AIB5510_AWA_5510.CCY;
        AICPQMIB.INPUT_DATA.TX_FLG = 'O';
        S000_CALL_AIZPQMIB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AICPQMIB.OUTPUT_DATA.ONL_FLG);
        if (AICPQMIB.OUTPUT_DATA.ONL_FLG != 'N') {
            CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_MIB_ONLFLG_NOTN);
        }
    }
    public void B020_BRW_REC_PROC() throws IOException,SQLException,Exception {
        AIRPMIB.BR = AIB5510_AWA_5510.BR;
        AIRPMIB.CCY = AIB5510_AWA_5510.CCY;
        AIRPMIB.ITM_NO = AIB5510_AWA_5510.ITM;
        AIRPMIB.SEQ = AIB5510_AWA_5510.SEQ;
        AIRPMIB.KEY.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_STARTBR_AITPMIB();
        if (pgmRtn) return;
        T000_READNEXT_AITPMIB();
        if (pgmRtn) return;
        if (WS_PMIB_FLG == 'Y') {
            B060_01_OUT_TITLE();
            if (pgmRtn) return;
        }
        WS_SUM_AMT.WS_DCNT = 0;
        WS_SUM_AMT.WS_CCNT = 0;
        WS_SUM_AMT.WS_DAMT = 0;
        WS_SUM_AMT.WS_CAMT = 0;
        WS_BRW_OUTPUT.WS_BRW_DCNT = 0;
        WS_BRW_OUTPUT.WS_BRW_CCNT = 0;
        while (WS_PMIB_FLG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            B060_02_OUT_BRW_DATA();
            if (pgmRtn) return;
            T000_READNEXT_AITPMIB();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_SUM_AMT.WS_DAMT);
        CEP.TRC(SCCGWA, WS_SUM_AMT.WS_CAMT);
        CEP.TRC(SCCGWA, WS_SUM_AMT.WS_DCNT);
        CEP.TRC(SCCGWA, WS_SUM_AMT.WS_CCNT);
        WS_BRW_OUTPUT.WS_BRW_DAMT = WS_SUM_AMT.WS_DAMT;
        WS_BRW_OUTPUT.WS_BRW_CAMT = WS_SUM_AMT.WS_CAMT;
        WS_BRW_OUTPUT.WS_BRW_DCNT = WS_SUM_AMT.WS_DCNT;
        WS_BRW_OUTPUT.WS_BRW_CCNT = WS_SUM_AMT.WS_CCNT;
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_BRW_CCNT);
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_BRW_DCNT);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_BRW_OUTPUT);
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_BRW_OUTPUT);
        SCCMPAG.DATA_LEN = 103;
        B_MPAG();
        if (pgmRtn) return;
        T000_ENDBR_AITPMIB();
        if (pgmRtn) return;
    }
    public void B060_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 103;
        SCCMPAG.SCR_ROW_CNT = 20;
        SCCMPAG.SCR_COL_CNT = 5;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B060_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_BRW_OUTPUT);
        WS_BRW_OUTPUT.WS_BRW_TRDATE = SCCGWA.COMM_AREA.AC_DATE;
        WS_BRW_OUTPUT.WS_BRW_BR = AIRPMIB.BR;
        WS_BRW_OUTPUT.WS_BRW_CCY = AIRPMIB.CCY;
        WS_BRW_OUTPUT.WS_BRW_ITM = AIRPMIB.ITM_NO;
        WS_BRW_OUTPUT.WS_BRW_SEQ = AIRPMIB.SEQ;
        WS_BRW_OUTPUT.WS_BRW_DRCRFLG = AIRPMIB.DR_CR;
        if (WS_BRW_OUTPUT.WS_BRW_DRCRFLG == 'C') {
            WS_SUM_AMT.WS_CAMT = WS_SUM_AMT.WS_CAMT + AIRPMIB.AMT;
            WS_SUM_AMT.WS_CCNT = WS_SUM_AMT.WS_CCNT + 1;
        }
        if (WS_BRW_OUTPUT.WS_BRW_DRCRFLG == 'D') {
            WS_SUM_AMT.WS_DAMT = WS_SUM_AMT.WS_DAMT + AIRPMIB.AMT;
            WS_SUM_AMT.WS_DCNT = WS_SUM_AMT.WS_DCNT + 1;
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        CEP.TRC(SCCGWA, BPCPQORG.RC.RC_CODE);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void T000_STARTBR_AITPMIB() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AIRPMIB.BR);
        CEP.TRC(SCCGWA, AIRPMIB.CCY);
        CEP.TRC(SCCGWA, AIRPMIB.ITM_NO);
        CEP.TRC(SCCGWA, AIRPMIB.SEQ);
        AITPMIB_BR.rp = new DBParm();
        AITPMIB_BR.rp.TableName = "AITPMIB";
        AITPMIB_BR.rp.where = "SEQ = :AIRPMIB.SEQ "
            + "AND BR = :AIRPMIB.BR "
            + "AND CCY = :AIRPMIB.CCY "
            + "AND ITM_NO = :AIRPMIB.ITM_NO "
            + "AND AC_DATE = :AIRPMIB.KEY.AC_DATE";
        IBS.STARTBR(SCCGWA, AIRPMIB, this, AITPMIB_BR);
    }
    public void T000_READNEXT_AITPMIB() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, AIRPMIB, this, AITPMIB_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_PMIB_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_PMIB_FLG = 'N';
        } else {
        }
    }
    public void T000_ENDBR_AITPMIB() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, AITPMIB_BR);
    }
    public void S000_CALL_AIZPQMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-MIB", AICPQMIB);
        if (AICPQMIB.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICPQMIB.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
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
