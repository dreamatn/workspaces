package com.hisun.LN;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.CI.CICCUST;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class LNZSINTA {
    boolean pgmRtn = false;
    String CPN_F_R_MAIN_ICTL = "LN-RSC-LNTICTL";
    String CPN_F_R_MAIN_INTA = "LN-RSC-LNTINTA";
    String CPN_F_R_MAIN_PLPI = "LN-RSC-LNTPLPI";
    String WS_ERR_MSG = " ";
    short WS_CNT = 0;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    LNRICTL LNRICTL = new LNRICTL();
    LNCRICTL LNCRICTL = new LNCRICTL();
    LNCSINTA LNCUINTA = new LNCSINTA();
    LNCIPART LNCIPART = new LNCIPART();
    CICCUST CICCUST = new CICCUST();
    SCCGWA SCCGWA;
    LNCSINTA LNCSINTA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, LNCSINTA LNCSINTA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSINTA = LNCSINTA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        C000_DATA_BACK();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSINTA return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCUINTA);
        LNCUINTA.FUNC = LNCSINTA.FUNC;
        LNCUINTA.CTA_NO = LNCSINTA.CTA_NO;
        LNCUINTA.CI_NO = LNCSINTA.CI_NO;
        LNCUINTA.CI_ENMS = LNCSINTA.CI_ENMS;
        LNCUINTA.CI_CNM = LNCSINTA.CI_CNM;
        LNCUINTA.PRIN = LNCSINTA.PRIN;
        LNCUINTA.OSBAL = LNCSINTA.OSBAL;
        LNCUINTA.CCY = LNCSINTA.CCY;
        LNCUINTA.STS = LNCSINTA.STS;
        LNCUINTA.PAY_TYP = LNCSINTA.PAY_TYP;
        LNCUINTA.PAY_TERM = LNCSINTA.PAY_TERM;
        LNCUINTA.VAL_DT = LNCSINTA.VAL_DT;
        LNCUINTA.DUE_DT = LNCSINTA.DUE_DT;
        LNCUINTA.INT = LNCSINTA.INT;
        LNCUINTA.OVE_AMTS = LNCSINTA.OVE_AMTS;
        LNCUINTA.ADJ_AMT = LNCSINTA.ADJ_AMT;
        LNCUINTA.ADJ_AMTS = LNCSINTA.ADJ_AMTS;
        LNCUINTA.LVE_AMTS = LNCSINTA.LVE_AMTS;
        LNCUINTA.TOT_L_INT = LNCSINTA.TOT_L_INT;
        LNCUINTA.ADJ_L_AMT = LNCSINTA.ADJ_L_AMT;
        LNCUINTA.RSN = LNCSINTA.RSN;
        LNCUINTA.SEQ = LNCSINTA.SEQ;
        LNCUINTA.LC_TYP = LNCSINTA.LC_TYP;
        S000_CALL_LNZUINTA();
        if (pgmRtn) return;
    }
    public void C000_DATA_BACK() throws IOException,SQLException,Exception {
        LNCSINTA.FUNC = LNCUINTA.FUNC;
        LNCSINTA.CTA_NO = LNCUINTA.CTA_NO;
        LNCSINTA.CI_NO = LNCUINTA.CI_NO;
        LNCSINTA.CI_ENMS = LNCUINTA.CI_ENMS;
        LNCSINTA.CI_CNM = LNCUINTA.CI_CNM;
        LNCSINTA.PRIN = LNCUINTA.PRIN;
        LNCSINTA.OSBAL = LNCUINTA.OSBAL;
        LNCSINTA.CCY = LNCUINTA.CCY;
        LNCSINTA.STS = LNCUINTA.STS;
        LNCSINTA.PAY_TYP = LNCUINTA.PAY_TYP;
        LNCSINTA.PAY_TERM = LNCUINTA.PAY_TERM;
        LNCSINTA.VAL_DT = LNCUINTA.VAL_DT;
        LNCSINTA.DUE_DT = LNCUINTA.DUE_DT;
        LNCSINTA.INT = LNCUINTA.INT;
        LNCSINTA.OVE_AMTS = LNCUINTA.OVE_AMTS;
        LNCSINTA.TOT_INT = LNCUINTA.TOT_INT;
        LNCSINTA.ADJ_AMT = LNCUINTA.ADJ_AMT;
        LNCSINTA.ADJ_AMTS = LNCUINTA.ADJ_AMTS;
        LNCSINTA.LVE_AMTS = LNCUINTA.LVE_AMTS;
        LNCSINTA.TOT_L_INT = LNCUINTA.TOT_L_INT;
        LNCSINTA.ADJ_L_AMT = LNCUINTA.ADJ_L_AMT;
        LNCSINTA.RSN = LNCUINTA.RSN;
        LNCSINTA.SEQ = LNCUINTA.SEQ;
        LNCSINTA.D_PRIN = LNCUINTA.D_PRIN;
        LNCSINTA.D_INT = LNCUINTA.D_INT;
    }
    public void D000_PART_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCIPART.DATA);
        LNCIPART.DATA.FUNC = 'T';
        LNCIPART.DATA.LEVEL = 'R';
        LNCIPART.DATA.CONTRACT_NO = LNCSINTA.CTA_NO;
        CEP.TRC(SCCGWA, LNCIPART.DATA.IS_SYN);
        CEP.TRC(SCCGWA, LNCIPART.DATA.CNT);
        CEP.TRC(SCCGWA, LNCSINTA.PAY_TYP);
        if (LNCIPART.DATA.IS_SYN != 'N') {
            WS_CNT = 1;
            while (WS_CNT <= LNCIPART.DATA.CNT 
                && WS_CNT != 99) {
                CEP.TRC(SCCGWA, WS_CNT);
                CEP.TRC(SCCGWA, LNCIPART.GROUP.get(WS_CNT-1).ADJ_BK_FLG);
                if (LNCIPART.GROUP.get(WS_CNT-1).ADJ_BK_FLG == 'A' 
                    || (LNCIPART.GROUP.get(WS_CNT-1).ADJ_BK_FLG == 'I' 
                    && (LNCSINTA.PAY_TYP == 'I' 
                    || LNCSINTA.PAY_TYP == 'L')) 
                    || (LNCIPART.GROUP.get(WS_CNT-1).ADJ_BK_FLG == 'P' 
                    && LNCSINTA.PAY_TYP == 'O')) {
                    IBS.init(SCCGWA, CICCUST);
                    S000_CALL_CIZCUST();
                    if (pgmRtn) return;
                    LNCUINTA.CI_ENM = CICCUST.O_DATA.O_CI_ENM;
                    LNCUINTA.CI_CNM = CICCUST.O_DATA.O_CI_NM;
                    LNCUINTA.SUB_CTA_NO = LNCIPART.GROUP.get(WS_CNT-1).SEQ_NO;
                    LNCUINTA.SEQ = 0;
                    CEP.TRC(SCCGWA, ";;;;;;;;;;;;;;;;;;;;;;;;;");
                    CEP.TRC(SCCGWA, LNCSINTA.CTA_NO);
                    CEP.TRC(SCCGWA, LNCSINTA.SUB_CTA_NO);
                    S000_CALL_LNZUINTA();
                    if (pgmRtn) return;
                    WS_CNT = 98;
                }
                WS_CNT = (short) (WS_CNT + 1);
            }
        }
    }
    public void S000_CALL_LNZUINTA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-UNT-ADJ-INTA", LNCUINTA);
        if (LNCUINTA.RC.RC_RTNCODE != 0) {
