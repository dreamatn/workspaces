package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class BPZSTHOL {
    boolean pgmRtn = false;
    String K_CPN_U_MAINTAIN_THOL = "BP-U-MAINTAIN-THOL";
    String K_HIS_COPYBOOK_NAME = "BPTTHOL";
    String K_FMT_CD_1 = "BPX01";
    String K_FMT_CD_2 = "BP745";
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    int WS_J = 0;
    int WS_K = 0;
    int WS_L = 0;
    BPZSTHOL_WS_HOL_WEEK_IFO WS_HOL_WEEK_IFO = new BPZSTHOL_WS_HOL_WEEK_IFO();
    BPZSTHOL_WS_HOL_IFO WS_HOL_IFO = new BPZSTHOL_WS_HOL_IFO();
    String WS_TS = " ";
    BPZSTHOL_WS_M_KEY WS_M_KEY = new BPZSTHOL_WS_M_KEY();
    BPZSTHOL_WS_M_DATA[] WS_M_DATA = new BPZSTHOL_WS_M_DATA[50];
    int WS_M_LAST_DATE = 0;
    String WS_M_LAST_TELLER = " ";
    char WS_OUTPUT_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    BPCTHOL BPCTHOL = new BPCTHOL();
    SCCGWA SCCGWA;
    BPCSTHOL BPCSTHOL;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public BPZSTHOL() {
        for (int i=0;i<50;i++) WS_M_DATA[i] = new BPZSTHOL_WS_M_DATA();
    }
    public void MP(SCCGWA SCCGWA, BPCSTHOL BPCSTHOL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSTHOL = BPCSTHOL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSTHOL return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSTHOL);
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        if (BPCSTHOL.FUNC == 'Q') {
            B010_QUERY_THOL_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSTHOL.FUNC == 'A') {
            B020_ADD_THOL_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSTHOL.FUNC == 'U') {
            B030_UPDATE_THOL_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSTHOL.FUNC == 'D') {
            B040_DELETE_THOL_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSTHOL.FUNC == 'B') {
            B050_BROWSE_THOL_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSTHOL.FUNC == 'M') {
            B060_MODIFY_ADD_THOL_PROCESS();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCSTHOL.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSTHOL.FUNC);
        if (BPCSTHOL.FUNC != 'Q' 
            && BPCSTHOL.FUNC != 'A' 
            && BPCSTHOL.FUNC != 'U' 
            && BPCSTHOL.FUNC != 'B' 
            && BPCSTHOL.FUNC != 'D' 
            && BPCSTHOL.FUNC != 'M') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCSTHOL.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        CEP.TRC(SCCGWA, BPCSTHOL.FUNC);
        if (BPCSTHOL.FUNC == 'Q' 
            || BPCSTHOL.FUNC == 'A' 
            || BPCSTHOL.FUNC == 'U' 
            || BPCSTHOL.FUNC == 'D') {
            if (BPCSTHOL.KEY.CAL_CD.trim().length() == 0 
                && BPCSTHOL.KEY.EFF_DT == ' ') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_HOL_M_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (BPCSTHOL.FUNC == 'A' 
            || BPCSTHOL.FUNC == 'U') {
            for (WS_I = 1; WS_I <= 50; WS_I += 1) {
                if (BPCSTHOL.HOL_DATA[WS_I-1].HOL_DT == 0) {
                    CEP.TRC(SCCGWA, BPCSTHOL.HOL_DATA[WS_I-1].HOL_DT);
                    WS_J = WS_J + 1;
                }
            }
        }
    }
    public void B010_QUERY_THOL_PROCESS() throws IOException,SQLException,Exception {
        S010_CALL_BPZUTHOL();
        if (pgmRtn) return;
        if (BPCSTHOL.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSTHOL.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        R000_OUTPUT_HOL_DATA_PROCESS();
        if (pgmRtn) return;
    }
    public void B020_ADD_THOL_PROCESS() throws IOException,SQLException,Exception {
        S010_CALL_BPZUTHOL();
        if (pgmRtn) return;
