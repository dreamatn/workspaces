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

public class BPZSTWND {
    boolean pgmRtn = false;
    String K_CPN_U_MAINTAIN_TWND = "BP-U-MAINTAIN-TWND";
    String K_HIS_COPYBOOK_NAME = "BPTTWND";
    String K_FMT_CD_1 = "BPX01";
    String K_FMT_CD_3 = "BP747";
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    int WS_J = 0;
    BPZSTWND_WS_HOL_WEEK_IFO WS_HOL_WEEK_IFO = new BPZSTWND_WS_HOL_WEEK_IFO();
    BPZSTWND_WS_WEEK_KEY WS_WEEK_KEY = new BPZSTWND_WS_WEEK_KEY();
    BPZSTWND_WS_WEEK_DATA[] WS_WEEK_DATA = new BPZSTWND_WS_WEEK_DATA[7];
    int WS_M_LAST_DATE = 0;
    String WS_M_LAST_TELLER = " ";
    String WS_TS = " ";
    char WS_OUTPUT_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    BPCTWND BPCTWND = new BPCTWND();
    SCCGWA SCCGWA;
    BPCSTWND BPCSTWND;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public BPZSTWND() {
        for (int i=0;i<7;i++) WS_WEEK_DATA[i] = new BPZSTWND_WS_WEEK_DATA();
    }
    public void MP(SCCGWA SCCGWA, BPCSTWND BPCSTWND) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSTWND = BPCSTWND;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSTWND return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSTWND);
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        if (BPCSTWND.FUNC == 'Q') {
            B010_QUERY_TWND_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSTWND.FUNC == 'A') {
            B020_ADD_TWND_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSTWND.FUNC == 'U') {
            B030_UPDATE_TWND_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSTWND.FUNC == 'D') {
            B040_DELETE_TWND_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSTWND.FUNC == 'B') {
            B050_BROWSE_TWND_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSTWND.FUNC == 'M') {
            B060_MODIFY_ADD_TWND_PROCESS();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCSTWND.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSTWND.FUNC);
        if (BPCSTWND.FUNC != 'Q' 
            && BPCSTWND.FUNC != 'A' 
            && BPCSTWND.FUNC != 'U' 
            && BPCSTWND.FUNC != 'B' 
            && BPCSTWND.FUNC != 'D' 
            && BPCSTWND.FUNC != 'M') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCSTWND.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        if (BPCSTWND.FUNC == 'Q' 
            || BPCSTWND.FUNC == 'A' 
            || BPCSTWND.FUNC == 'U' 
            || BPCSTWND.FUNC == 'D') {
            if (BPCSTWND.KEY.EFF_DATE == ' ') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_HOL_M_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        WS_I = 0;
        WS_J = 0;
        if (BPCSTWND.FUNC == 'A' 
            || BPCSTWND.FUNC == 'U') {
            for (WS_I = 1; WS_I <= 7; WS_I += 1) {
                if (BPCSTWND.HOL_DATA[WS_I-1].WND_NO == 0) {
                    WS_J = WS_J + 1;
                }
            }
            CEP.TRC(SCCGWA, WS_J);
            if (WS_J > 3) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_WEEKEND_TOO_MANY;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B010_QUERY_TWND_PROCESS() throws IOException,SQLException,Exception {
        S010_CALL_BPZUTWND();
        if (pgmRtn) return;
        if (BPCSTWND.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSTWND.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        R000_OUTPUT_HOL_DATA_PROCESS();
        if (pgmRtn) return;
    }
    public void B020_ADD_TWND_PROCESS() throws IOException,SQLException,Exception {
        B020_01_CHECK_PROCESS();
        if (pgmRtn) return;
        for (WS_I = 1; WS_I <= 7; WS_I += 1) {
            if (WS_WEEK_DATA[WS_I-1].WS_M_WND_OPT == ' ') {
                BPCSTWND.HOL_DATA[WS_I-1].WND_OPT = 'A';
            }
            CEP.TRC(SCCGWA, BPCSTWND);
        }
        S010_CALL_BPZUTWND();
        if (pgmRtn) return;
