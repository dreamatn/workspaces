package com.hisun.AI;

import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class AIZSITAD {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    AIZSITAD_WS_RTN_TERM WS_RTN_TERM = new AIZSITAD_WS_RTN_TERM();
    AIZSITAD_WS_OUTPUT_ITUS WS_OUTPUT_ITUS = new AIZSITAD_WS_OUTPUT_ITUS();
    AIZSITAD_WS_ITAD_DATA WS_ITAD_DATA = new AIZSITAD_WS_ITAD_DATA();
    AIZSITAD_WS_ITAD_DATA2 WS_ITAD_DATA2 = new AIZSITAD_WS_ITAD_DATA2();
    char WS_MOD_DEL_FLG = ' ';
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    AIRITUS AIRITUS = new AIRITUS();
    AIRITAD AIRITAD = new AIRITAD();
    AIRITAD AIRBITAD = new AIRITAD();
    AIRITM AIRITM = new AIRITM();
    AICRITAD AICRITAD = new AICRITAD();
    AICPQITM AICQITM = new AICPQITM();
    SCCGWA SCCGWA;
    AICSITAD AICSITAD;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGAPV SCCGAPV;
    public void MP(SCCGWA SCCGWA, AICSITAD AICSITAD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICSITAD = AICSITAD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_TRAN_DATA();
        if (pgmRtn) return;
        B100_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZSITAD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
        AIRITAD.KEY.PROC_DATE = AICSITAD.COMM_DATA.ADJ_DATE;
        AIRITAD.KEY.COA_FLG = AICSITAD.COMM_DATA.COA_TYP;
        AIRITAD.KEY.ITM_NO = AICSITAD.COMM_DATA.ITM_OLD;
        AIRITAD.FUNC_FLG = '1';
        WS_ITAD_DATA.WS_ITM_NEW1 = AICSITAD.COMM_DATA.ITM_NEW;
        WS_ITAD_DATA.WS_PRC_STS1 = 'U';
        WS_ITAD_DATA.WS_ITMNEW_S1 = AICSITAD.COMM_DATA.ITMNEW_S;
        AIRITAD.DATA = IBS.CLS2CPY(SCCGWA, WS_ITAD_DATA);
    }
    public void B000_TRAN_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICRITAD);
        WS_RTN_TERM.WS_ADJ_DATE = AICSITAD.COMM_DATA.ADJ_DATE;
        WS_RTN_TERM.WS_COA_TYP = AICSITAD.COMM_DATA.COA_TYP;
        WS_RTN_TERM.WS_ITM_OLD = AICSITAD.COMM_DATA.ITM_OLD;
        WS_RTN_TERM.WS_ITM_NEW = AICSITAD.COMM_DATA.ITM_NEW;
        WS_RTN_TERM.WS_FUNC_FLG = AICSITAD.COMM_DATA.FUNC_FLG;
        WS_RTN_TERM.WS_ITMOLD_D = AICSITAD.COMM_DATA.ITMOLD_D;
        WS_RTN_TERM.WS_ITMNEW_D = AICSITAD.COMM_DATA.ITMNEW_D;
        WS_RTN_TERM.WS_ITMNEW_S = AICSITAD.COMM_DATA.ITMNEW_S;
        CEP.TRC(SCCGWA, AICSITAD.COMM_DATA.ITM_OLD);
        CEP.TRC(SCCGWA, AIRITAD.KEY.ITM_NO);
        CEP.TRC(SCCGWA, AICSITAD.COMM_DATA.ITMOLD_D);
    }
    public void B100_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "CC11");
        if (AICSITAD.FUNC == 'A') {
            B010_ADD_PROC();
            if (pgmRtn) return;
        } else if (AICSITAD.FUNC == 'M') {
            B030_MOD_PROC();
            if (pgmRtn) return;
        } else if (AICSITAD.FUNC == 'D') {
            B050_DEL_PROC();
            if (pgmRtn) return;
        } else if (AICSITAD.FUNC == 'B') {
            B080_BRW_PROC();
            if (pgmRtn) return;
        } else if (AICSITAD.FUNC == 'I') {
            B080_INQ_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + AICSITAD.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_ADD_PROC() throws IOException,SQLException,Exception {
        AICRITAD.FUNC = 'A';
        AIRITAD.KEY.PROC_DATE = AICSITAD.COMM_DATA.ADJ_DATE;
        AIRITAD.KEY.COA_FLG = AICSITAD.COMM_DATA.COA_TYP;
        AIRITAD.KEY.ITM_NO = AICSITAD.COMM_DATA.ITM_OLD;
        AIRITAD.FUNC_FLG = '1';
        WS_ITAD_DATA.WS_ITM_NEW1 = AICSITAD.COMM_DATA.ITM_NEW;
        WS_ITAD_DATA.WS_PRC_STS1 = 'U';
        if (AICSITAD.COMM_DATA.FUNC_FLG == 'C' 
            || AICSITAD.COMM_DATA.FUNC_FLG == 'M' 
            || AICSITAD.COMM_DATA.FUNC_FLG == 'D' 
            || AICSITAD.COMM_DATA.FUNC_FLG == 'R') {
            CEP.TRC(SCCGWA, AICSITAD.COMM_DATA.JRN_NO);
            AIRITAD.KEY.SET_NO = "" + AICSITAD.COMM_DATA.JRN_NO;
            JIBS_tmp_int = AIRITAD.KEY.SET_NO.length();
            for (int i=0;i<12-JIBS_tmp_int;i++) AIRITAD.KEY.SET_NO = "0" + AIRITAD.KEY.SET_NO;
            AIRITAD.FUNC_FLG = AICSITAD.COMM_DATA.FUNC_FLG;
            WS_ITAD_DATA.WS_PRC_STS1 = AICSITAD.COMM_DATA.FUNC_FLG;
        }
        AIRITAD.DATA = IBS.CLS2CPY(SCCGWA, WS_ITAD_DATA);
        AIRITAD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AIRITAD.LAST_UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_AIZRITAD();
        if (pgmRtn) return;
        if (AICSITAD.COMM_DATA.FUNC_FLG == 'C' 
            || AICSITAD.COMM_DATA.FUNC_FLG == 'M' 
            || AICSITAD.COMM_DATA.FUNC_FLG == 'D' 
            || AICSITAD.COMM_DATA.FUNC_FLG == 'R') {
        } else {
            S000_RTN_PROC();
            if (pgmRtn) return;
        }
    }
    public void B080_INQ_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICRITAD);
        IBS.init(SCCGWA, AIRITAD);
        AICRITAD.FUNC = 'I';
        AIRITAD.KEY.PROC_DATE = AICSITAD.COMM_DATA.ADJ_DATE;
        AIRITAD.KEY.COA_FLG = AICSITAD.COMM_DATA.COA_TYP;
        AIRITAD.KEY.ITM_NO = AICSITAD.COMM_DATA.ITM_OLD;
        AIRITAD.FUNC_FLG = '1';
        WS_ITAD_DATA.WS_ITM_NEW1 = AICSITAD.COMM_DATA.ITM_NEW;
        WS_ITAD_DATA.WS_PRC_STS1 = 'U';
        AIRITAD.DATA = IBS.CLS2CPY(SCCGWA, WS_ITAD_DATA);
        AIRITAD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AIRITAD.LAST_UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_AIZRITAD();
        if (pgmRtn) return;
        S000_RTN_PROC();
        if (pgmRtn) return;
    }
    public void B030_MOD_PROC() throws IOException,SQLException,Exception {
        AIRITAD.KEY.COA_FLG = AICSITAD.COMM_DATA.COA_TYP;
        AIRITAD.KEY.ITM_NO = AICSITAD.COMM_DATA.ITM_OLD;
        AIRITAD.FUNC_FLG = '1';
        AICRITAD.FUNC = 'B';
        AICRITAD.OPT = 'O';
        S000_CALL_AIZRITAD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDD");
        AICRITAD.OPT = 'R';
        S000_CALL_AIZRITAD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DJDTEST");
        CEP.TRC(SCCGWA, AIRITAD.KEY.COA_FLG);
        CEP.TRC(SCCGWA, AIRITAD.KEY.ITM_NO);
        CEP.TRC(SCCGWA, AIRITAD.FUNC_FLG);
        CEP.TRC(SCCGWA, AIRITAD.KEY.PROC_DATE);
        CEP.TRC(SCCGWA, AICSITAD.COMM_DATA.ADJ_DATE);
        while (AICRITAD.RETURN_INFO != 'N') {
            IBS.CPY2CLS(SCCGWA, AIRITAD.DATA, WS_ITAD_DATA2);
            CEP.TRC(SCCGWA, AIRITAD.KEY.PROC_DATE);
            CEP.TRC(SCCGWA, WS_ITAD_DATA2.WS_PRC_STS2);
            if (WS_ITAD_DATA2.WS_PRC_STS2 == 'U') {
                if (AICSITAD.COMM_DATA.ADJ_DATE != AIRITAD.KEY.PROC_DATE) {
                    WS_MOD_DEL_FLG = 'Y';
                    B050_DEL_PROC();
                    if (pgmRtn) return;
                    B010_ADD_PROC();
                    if (pgmRtn) return;
                } else {
                    AICRITAD.FUNC = 'R';
                    AIRITAD.KEY.PROC_DATE = AICSITAD.COMM_DATA.ADJ_DATE;
                    AIRITAD.KEY.COA_FLG = AICSITAD.COMM_DATA.COA_TYP;
                    AIRITAD.KEY.ITM_NO = AICSITAD.COMM_DATA.ITM_OLD;
                    AIRITAD.FUNC_FLG = '1';
                    S000_CALL_AIZRITAD();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, AIRITAD.KEY.PROC_DATE);
                    CEP.TRC(SCCGWA, AIRITAD.KEY.COA_FLG);
                    CEP.TRC(SCCGWA, AIRITAD.KEY.ITM_NO);
                    CEP.TRC(SCCGWA, AIRITAD.FUNC_FLG);
                    AICRITAD.FUNC = 'M';
                    AIRITAD.KEY.PROC_DATE = AICSITAD.COMM_DATA.ADJ_DATE;
                    AIRITAD.KEY.COA_FLG = AICSITAD.COMM_DATA.COA_TYP;
                    AIRITAD.KEY.ITM_NO = AICSITAD.COMM_DATA.ITM_OLD;
                    AIRITAD.FUNC_FLG = '1';
                    WS_ITAD_DATA.WS_ITM_NEW1 = AICSITAD.COMM_DATA.ITM_NEW;
                    WS_ITAD_DATA.WS_PRC_STS1 = 'U';
                    AIRITAD.DATA = IBS.CLS2CPY(SCCGWA, WS_ITAD_DATA);
                    AIRITAD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    AIRITAD.LAST_UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                    S000_CALL_AIZRITAD();
                    if (pgmRtn) return;
                }
            }
            AICRITAD.FUNC = 'B';
            AICRITAD.OPT = 'R';
            S000_CALL_AIZRITAD();
            if (pgmRtn) return;
        }
        AICRITAD.OPT = 'E';
        S000_CALL_AIZRITAD();
        if (pgmRtn) return;
        AICRITAD.FUNC = 'M';
        S000_RTN_PROC();
        if (pgmRtn) return;
    }
    public void B050_DEL_PROC() throws IOException,SQLException,Exception {
        AICRITAD.FUNC = 'R';
        S000_CALL_AIZRITAD();
        if (pgmRtn) return;
        AIRITAD.KEY.COA_FLG = AICSITAD.COMM_DATA.COA_TYP;
        AIRITAD.KEY.ITM_NO = AICSITAD.COMM_DATA.ITM_OLD;
        AIRITAD.FUNC_FLG = '1';
        CEP.TRC(SCCGWA, WS_MOD_DEL_FLG);
        if (WS_MOD_DEL_FLG == 'Y') {
        } else {
            AIRITAD.KEY.PROC_DATE = AICSITAD.COMM_DATA.ADJ_DATE;
        }
        AIRITAD.KEY.COA_FLG = AICSITAD.COMM_DATA.COA_TYP;
        AICRITAD.FUNC = 'D';
        S000_CALL_AIZRITAD();
        if (pgmRtn) return;
        S000_RTN_PROC();
        if (pgmRtn) return;
    }
    public void B080_BRW_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "STARTBR");
        AICRITAD.FUNC = 'B';
        AICRITAD.OPT = 'S';
        S000_CALL_AIZRITAD();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            IBS.init(SCCGWA, AICQITM);
            AICQITM.INPUT_DATA.COA_FLG = AICSITAD.COMM_DATA.COA_TYP;
            AICQITM.INPUT_DATA.NO = AIRITAD.KEY.ITM_NO;
            S000_CALL_AIZQITM();
            if (pgmRtn) return;
            AICSITAD.COMM_DATA.ITMOLD_D = AICQITM.OUTPUT_DATA.CHS_NM;
            IBS.init(SCCGWA, AICQITM);
            AICQITM.INPUT_DATA.COA_FLG = AICSITAD.COMM_DATA.COA_TYP;
            IBS.CPY2CLS(SCCGWA, AIRITAD.DATA, WS_ITAD_DATA);
            AICQITM.INPUT_DATA.NO = WS_ITAD_DATA.WS_ITM_NEW1;
            S000_CALL_AIZQITM();
            if (pgmRtn) return;
            AICSITAD.COMM_DATA.ITMNEW_D = AICQITM.OUTPUT_DATA.CHS_NM;
            AICSITAD.COMM_DATA.PRC_STS = WS_ITAD_DATA.WS_PRC_STS1;
            AICRITAD.FUNC = 'B';
            S000_RTN_PROC();
            if (pgmRtn) return;
            AICRITAD.OPT = 'R';
            S000_CALL_AIZRITAD();
            if (pgmRtn) return;
        }
        AICRITAD.FUNC = 'B';
        AICRITAD.OPT = 'E';
        S000_CALL_AIZRITAD();
        if (pgmRtn) return;
    }
    public void S000_CALL_AIZRITAD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "BB91");
        CEP.TRC(SCCGWA, WS_RTN_TERM);
        AICRITAD.REC_PTR = AIRITAD;
        AICRITAD.REC_LEN = 579;
        CEP.TRC(SCCGWA, "BB92");
        IBS.CALLCPN(SCCGWA, "AI-SRC-AIZRITAD", AICRITAD);
        CEP.TRC(SCCGWA, "BB93");
        CEP.TRC(SCCGWA, AICRITAD.RC.RC_CODE);
        CEP.TRC(SCCGWA, AIRITAD.DATA);
        if (AICRITAD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICRITAD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_RTN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        IBS.init(SCCGWA, WS_RTN_TERM);
        WS_RTN_TERM.WS_ADJ_DATE = AICSITAD.COMM_DATA.ADJ_DATE;
        WS_RTN_TERM.WS_COA_TYP = AICSITAD.COMM_DATA.COA_TYP;
        WS_RTN_TERM.WS_ITM_OLD = AICSITAD.COMM_DATA.ITM_OLD;
        if (AIRITAD.DATA == null) AIRITAD.DATA = "";
        JIBS_tmp_int = AIRITAD.DATA.length();
        for (int i=0;i<252-JIBS_tmp_int;i++) AIRITAD.DATA += " ";
        WS_RTN_TERM.WS_ITM_NEW = AIRITAD.DATA.substring(0, 10);
        WS_RTN_TERM.WS_FUNC_FLG = AICSITAD.COMM_DATA.FUNC_FLG;
        WS_RTN_TERM.WS_ITMOLD_D = AICSITAD.COMM_DATA.ITMOLD_D;
        WS_RTN_TERM.WS_ITMNEW_D = AICSITAD.COMM_DATA.ITMNEW_D;
        if (AIRITAD.DATA == null) AIRITAD.DATA = "";
        JIBS_tmp_int = AIRITAD.DATA.length();
        for (int i=0;i<252-JIBS_tmp_int;i++) AIRITAD.DATA += " ";
        WS_RTN_TERM.WS_PRC_STS = AIRITAD.DATA.substring(11 - 1, 11 + 1 - 1).charAt(0);
        CEP.TRC(SCCGWA, WS_RTN_TERM);
        SCCFMT.FMTID = "AIX71";
        SCCFMT.DATA_PTR = WS_RTN_TERM;
        SCCFMT.DATA_LEN = 360;
        if (AICRITAD.FUNC == 'B') {
            B_MPAG();
            if (pgmRtn) return;
        } else {
            IBS.FMT(SCCGWA, SCCFMT);
        }
    }
    public void S000_CALL_AIZQITM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "BB66A");
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-ITM", AICQITM);
        if (AICQITM.RC.RTNCODE != 0) {
            CEP.ERR(SCCGWA, AICQITM.RC.RTNCODE);
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
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
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
