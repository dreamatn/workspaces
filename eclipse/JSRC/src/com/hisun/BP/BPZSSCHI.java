package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSSCHI {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "SCZ01";
    int K_MAX_ROW = 99;
    int K_COL_CNT = 12;
    String CPN_R_BDW_SCHB = "BP-R-BDW-SCHB       ";
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    String WS_CODE_NO = " ";
    int WS_CNT = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPRSCHS BPRSCHS = new BPRSCHS();
    BPCRSCHB BPCRSCHB = new BPCRSCHB();
    BPCOSCHB BPCOSCHB = new BPCOSCHB();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCSSCHI BPCSSCHI;
    public void MP(SCCGWA SCCGWA, BPCSSCHI BPCSSCHI) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSSCHI = BPCSSCHI;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSSCHI return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCRSCHB);
        IBS.init(SCCGWA, BPRSCHS);
        IBS.init(SCCGWA, BPCSSCHI.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_BROWSE_DATA_PROC();
        if (pgmRtn) return;
    }
    public void B010_BROWSE_DATA_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSSCHI.TX_BR);
        CEP.TRC(SCCGWA, BPCSSCHI.TX_TLR);
        CEP.TRC(SCCGWA, BPCSSCHI.START_DT);
        CEP.TRC(SCCGWA, BPCSSCHI.END_DT);
        CEP.TRC(SCCGWA, BPCSSCHI.SC_TYPE);
        CEP.TRC(SCCGWA, BPCSSCHI.TX_TYPE);
        IBS.init(SCCGWA, BPCRSCHB);
        IBS.init(SCCGWA, BPRSCHS);
        BPRSCHS.KEY.BR = BPCSSCHI.TX_BR;
        BPRSCHS.TX_TLR = BPCSSCHI.TX_TLR;
        BPCRSCHB.STR_DATE = BPCSSCHI.START_DT;
        BPCRSCHB.END_DATE = BPCSSCHI.END_DT;
        BPRSCHS.SC_TYPE = BPCSSCHI.SC_TYPE;
        BPRSCHS.TX_TYPE = BPCSSCHI.TX_TYPE;
        if (BPCSSCHI.TX_TLR.trim().length() > 0 
                && BPCSSCHI.SC_TYPE != ' ' 
                && BPCSSCHI.TX_TYPE != ' ') {
            BPCRSCHB.FUNC = 'I';
        } else if (BPCSSCHI.TX_TLR.trim().length() == 0 
                && BPCSSCHI.SC_TYPE != ' ' 
                && BPCSSCHI.TX_TYPE != ' ') {
            BPCRSCHB.FUNC = 'H';
        } else if (BPCSSCHI.TX_TLR.trim().length() > 0 
                && BPCSSCHI.SC_TYPE == ' ' 
                && BPCSSCHI.TX_TYPE != ' ') {
            BPCRSCHB.FUNC = 'G';
        } else if (BPCSSCHI.TX_TLR.trim().length() > 0 
                && BPCSSCHI.SC_TYPE != ' ' 
                && BPCSSCHI.TX_TYPE == ' ') {
            BPCRSCHB.FUNC = 'F';
        } else if (BPCSSCHI.TX_TLR.trim().length() == 0 
                && BPCSSCHI.SC_TYPE == ' ' 
                && BPCSSCHI.TX_TYPE != ' ') {
            BPCRSCHB.FUNC = 'D';
        } else if (BPCSSCHI.TX_TLR.trim().length() == 0 
                && BPCSSCHI.SC_TYPE != ' ' 
                && BPCSSCHI.TX_TYPE == ' ') {
            BPCRSCHB.FUNC = 'C';
        } else if (BPCSSCHI.TX_TLR.trim().length() > 0 
                && BPCSSCHI.SC_TYPE == ' ' 
                && BPCSSCHI.TX_TYPE == ' ') {
            BPCRSCHB.FUNC = 'B';
        } else if (BPCSSCHI.TX_TLR.trim().length() == 0 
                && BPCSSCHI.SC_TYPE == ' ' 
                && BPCSSCHI.TX_TYPE == ' ') {
            BPCRSCHB.FUNC = 'A';
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCSSCHI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        BPCRSCHB.POINTER = BPRSCHS;
        BPCRSCHB.LEN = 565;
        S000_CALL_BPZRSCHB();
        if (pgmRtn) return;
        BPCRSCHB.FUNC = 'N';
        BPCRSCHB.POINTER = BPRSCHS;
        BPCRSCHB.LEN = 565;
        S000_CALL_BPZRSCHB();
        if (pgmRtn) return;
        B130_OUTPUT_TITLE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCRSCHB.RC);
        CEP.TRC(SCCGWA, BPCRSCHB.RETURN_INFO);
        for (WS_CNT = 1; BPCRSCHB.RETURN_INFO != 'N' 
            && SCCMPAG.FUNC != 'E'; WS_CNT += 1) {
            R000_TRANS_DATA_PARAMETER();
            if (pgmRtn) return;
            B140_OUTPUT_DETAIL();
            if (pgmRtn) return;
            BPCRSCHB.FUNC = 'N';
            BPCRSCHB.POINTER = BPRSCHS;
            BPCRSCHB.LEN = 565;
            S000_CALL_BPZRSCHB();
            if (pgmRtn) return;
        }
        BPCRSCHB.FUNC = 'E';
        BPCRSCHB.POINTER = BPRSCHS;
        BPCRSCHB.LEN = 565;
        S000_CALL_BPZRSCHB();
        if (pgmRtn) return;
    }
    public void B130_OUTPUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 534;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B140_OUTPUT_DETAIL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, BPCOSCHB);
        SCCMPAG.DATA_LEN = 534;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOSCHB);
        BPCOSCHB.BR = BPRSCHS.KEY.BR;
        BPCOSCHB.CODE_NO = BPRSCHS.KEY.CODE_NO;
        BPCOSCHB.SC_DATE = BPRSCHS.SC_DATE;
        BPCOSCHB.SC_TYPE = BPRSCHS.SC_TYPE;
        BPCOSCHB.MC_NO = BPRSCHS.MC_NO;
        BPCOSCHB.PL_BOX_NO = BPRSCHS.PL_BOX_NO;
        BPCOSCHB.TX_BR = BPRSCHS.TX_BR;
        BPCOSCHB.TX_TLR = BPRSCHS.TX_TLR;
        BPCOSCHB.TX_DATE = BPRSCHS.KEY.TX_DT;
        BPCOSCHB.TX_JRN = BPRSCHS.KEY.TX_JRN;
        BPCOSCHB.TX_CODE = BPRSCHS.TX_CODE;
        BPCOSCHB.REC_STS = BPRSCHS.REC_STS;
        BPCOSCHB.TX_TYPE = BPRSCHS.TX_TYPE;
        BPCOSCHB.DRW_NM = BPRSCHS.DRW_NM;
        BPCOSCHB.DRW_ID_TYP = BPRSCHS.DRW_ID_TP;
        BPCOSCHB.DRW_ID_NO = BPRSCHS.DRW_ID_NO;
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_BPZRSCHB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_BDW_SCHB, BPCRSCHB);
        if (BPCRSCHB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRSCHB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
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
