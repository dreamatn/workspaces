package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSIEC {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_BRW_PUB_PARM = "BP-PARM-BROWSE    ";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY    ";
    String K_PARM_TYPE = "CESQN";
    short WS_I = 0;
    String WS_CCY = " ";
    BPZSIEC_WS_CCY1_AREA WS_CCY1_AREA = new BPZSIEC_WS_CCY1_AREA();
    BPZSIEC_WS_CCY2_AREA WS_CCY2_AREA = new BPZSIEC_WS_CCY2_AREA();
    BPZSIEC_WS_FIX_AREA WS_FIX_AREA = new BPZSIEC_WS_FIX_AREA();
    BPZSIEC_WS_EXR_CODE WS_EXR_CODE = new BPZSIEC_WS_EXR_CODE();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCPRMB BPCPRMB = new BPCPRMB();
    BPREXSEQ BPREXSEQ = new BPREXSEQ();
    BPCPRMR BPCPRMR = new BPCPRMR();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCOIEC BPCOIEC;
    public void MP(SCCGWA SCCGWA, BPCOIEC BPCOIEC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCOIEC = BPCOIEC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSIEC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCOIEC.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_ROUTINE_PROC();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCOIEC.CCY1.trim().length() == 0) {
            if (BPCOIEC.EXR_CODE == null) BPCOIEC.EXR_CODE = "";
            JIBS_tmp_int = BPCOIEC.EXR_CODE.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) BPCOIEC.EXR_CODE += " ";
            BPCOIEC.CCY1 = BPCOIEC.EXR_CODE.substring(0, 3);
        }
        if (BPCOIEC.CCY2.trim().length() == 0) {
            if (BPCOIEC.EXR_CODE == null) BPCOIEC.EXR_CODE = "";
            JIBS_tmp_int = BPCOIEC.EXR_CODE.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) BPCOIEC.EXR_CODE += " ";
            BPCOIEC.CCY2 = BPCOIEC.EXR_CODE.substring(4 - 1, 4 + 3 - 1);
        }
        CEP.TRC(SCCGWA, "DEVHZ");
        if (BPCOIEC.CCY1.trim().length() == 0 
            || BPCOIEC.CCY2.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CCY_INPUT_ERR, BPCOIEC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "DEVHZ212");
        CEP.TRC(SCCGWA, BPCOIEC.CCY1);
        CEP.TRC(SCCGWA, BPCOIEC.CCY2);
        if (BPCOIEC.CCY1.equalsIgnoreCase(BPCOIEC.CCY2)) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_IN_DIF_CCY, BPCOIEC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCOIEC.CCY1);
        CEP.TRC(SCCGWA, BPCOIEC.CCY2);
        WS_CCY = BPCOIEC.CCY1;
        R000_CHECK_CCY();
        if (pgmRtn) return;
        WS_CCY = BPCOIEC.CCY2;
        R000_CHECK_CCY();
        if (pgmRtn) return;
    }
    public void B200_ROUTINE_PROC() throws IOException,SQLException,Exception {
        B210_GEN_EXR_CODE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCOIEC.EXR_CODE);
        if (BPCOIEC.EXR_CODE.trim().length() > 0) {
            B220_CHK_EXR_CODE();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "DEVHZ08042");
            BPCOIEC.EXR_CODE = IBS.CLS2CPY(SCCGWA, WS_EXR_CODE);
            CEP.TRC(SCCGWA, "DEVHZ08043");
            CEP.TRC(SCCGWA, WS_EXR_CODE);
            CEP.TRC(SCCGWA, BPCOIEC.EXR_CODE);
        }
    }
    public void B210_GEN_EXR_CODE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPREXSEQ);
        IBS.init(SCCGWA, BPCPRMB);
        IBS.init(SCCGWA, WS_CCY1_AREA);
        IBS.init(SCCGWA, WS_CCY2_AREA);
        IBS.init(SCCGWA, WS_FIX_AREA);
        CEP.TRC(SCCGWA, BPCOIEC.EXR_TYP);
        if (BPCOIEC.EXR_TYP.equalsIgnoreCase("TRU")) {
            CEP.TRC(SCCGWA, "TEST1111");
            BPREXSEQ.KEY.TYP = "CESQC";
        } else {
            CEP.TRC(SCCGWA, "TEST2222");
            BPREXSEQ.KEY.TYP = "CESQN";
        }
        IBS.init(SCCGWA, BPCPRMR);
        BPCPRMR.FUNC = ' ';
        if (BPCOIEC.EXR_TYP.equalsIgnoreCase("CRU")) {
            BPCPRMR.TYP = "CESQN";
        } else {
            BPCPRMR.TYP = "CESQN";
        }
        BPCPRMR.CD = "01";
        CEP.TRC(SCCGWA, "DEVHZ08047");
        CEP.TRC(SCCGWA, BPCPRMR.TYP);
        BPCPRMR.DAT_PTR = BPREXSEQ;
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DEVHZ08046");
        CEP.TRC(SCCGWA, WS_CCY1_AREA.WS_LVL1);
        CEP.TRC(SCCGWA, BPREXSEQ.DAT_TXT.FIX_IND);
        CEP.TRC(SCCGWA, "DEVHZ001");
        for (WS_I = 1; WS_I <= 18 
            && BPREXSEQ.DAT_TXT.CCY_DATA[WS_I-1].CCY.trim().length() != 0; WS_I += 1) {
            if (BPREXSEQ.DAT_TXT.CCY_DATA[WS_I-1].CCY.equalsIgnoreCase(BPCOIEC.CCY1) 
                && WS_CCY1_AREA.WS_LVL1 == 0) {
                if (BPREXSEQ.KEY.CD == null) BPREXSEQ.KEY.CD = "";
                JIBS_tmp_int = BPREXSEQ.KEY.CD.length();
                for (int i=0;i<40-JIBS_tmp_int;i++) BPREXSEQ.KEY.CD += " ";
                if (BPREXSEQ.KEY.CD.substring(0, 2).trim().length() == 0) WS_CCY1_AREA.WS_LVL1 = 0;
                else WS_CCY1_AREA.WS_LVL1 = Short.parseShort(BPREXSEQ.KEY.CD.substring(0, 2));
                WS_CCY1_AREA.WS_SEQ1 = WS_I;
                WS_CCY1_AREA.WS_FND1 = 1;
            }
            if (BPREXSEQ.DAT_TXT.CCY_DATA[WS_I-1].CCY.equalsIgnoreCase(BPCOIEC.CCY2) 
                && WS_CCY2_AREA.WS_LVL2 == 0) {
                if (BPREXSEQ.KEY.CD == null) BPREXSEQ.KEY.CD = "";
                JIBS_tmp_int = BPREXSEQ.KEY.CD.length();
                for (int i=0;i<40-JIBS_tmp_int;i++) BPREXSEQ.KEY.CD += " ";
                if (BPREXSEQ.KEY.CD.substring(0, 2).trim().length() == 0) WS_CCY2_AREA.WS_LVL2 = 0;
                else WS_CCY2_AREA.WS_LVL2 = Short.parseShort(BPREXSEQ.KEY.CD.substring(0, 2));
                WS_CCY2_AREA.WS_SEQ2 = WS_I;
                WS_CCY2_AREA.WS_FND2 = 1;
            }
            CEP.TRC(SCCGWA, BPREXSEQ.KEY.CD);
        }
        CEP.TRC(SCCGWA, "DEVHZ002");
        CEP.TRC(SCCGWA, WS_CCY1_AREA);
        CEP.TRC(SCCGWA, WS_CCY2_AREA);
        CEP.TRC(SCCGWA, WS_FIX_AREA);
        CEP.TRC(SCCGWA, BPCOIEC.EXR_FLG);
        if (WS_CCY1_AREA.WS_FND1 == 1 
            && WS_CCY2_AREA.WS_FND2 == 1 
            && BPCOIEC.EXR_FLG == 'N') {
            CEP.TRC(SCCGWA, "DEVHZ384");
            WS_EXR_CODE.WS_CCY1 = BPCOIEC.CCY1;
            WS_EXR_CODE.WS_CCY2 = BPCOIEC.CCY2;
        } else {
            if (WS_CCY1_AREA.WS_FND1 == 1 
                && WS_CCY2_AREA.WS_FND2 == 1) {
                if (WS_CCY1_AREA.WS_LVL1 == WS_CCY2_AREA.WS_LVL2) {
                    if (WS_CCY1_AREA.WS_SEQ1 > WS_CCY2_AREA.WS_SEQ2) {
                        WS_EXR_CODE.WS_CCY1 = BPCOIEC.CCY2;
                        WS_EXR_CODE.WS_CCY2 = BPCOIEC.CCY1;
                    } else {
                        WS_EXR_CODE.WS_CCY1 = BPCOIEC.CCY1;
                        WS_EXR_CODE.WS_CCY2 = BPCOIEC.CCY2;
                    }
                } else {
                    if (WS_CCY1_AREA.WS_LVL1 > WS_CCY2_AREA.WS_LVL2) {
                        WS_EXR_CODE.WS_CCY1 = BPCOIEC.CCY2;
                        WS_EXR_CODE.WS_CCY2 = BPCOIEC.CCY1;
                    } else {
                        WS_EXR_CODE.WS_CCY1 = BPCOIEC.CCY1;
                        WS_EXR_CODE.WS_CCY2 = BPCOIEC.CCY2;
                    }
                }
                CEP.TRC(SCCGWA, "DEVSOS1");
            }
        }
        CEP.TRC(SCCGWA, "DEVHZ08041");
        CEP.TRC(SCCGWA, WS_EXR_CODE.WS_CCY1);
        CEP.TRC(SCCGWA, WS_EXR_CODE.WS_CCY2);
    }
    public void B220_CHK_EXR_CODE() throws IOException,SQLException,Exception {
        if (BPCOIEC.EXR_CODE == null) BPCOIEC.EXR_CODE = "";
        JIBS_tmp_int = BPCOIEC.EXR_CODE.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCOIEC.EXR_CODE += " ";
        if (BPCOIEC.EXR_CODE == null) BPCOIEC.EXR_CODE = "";
        JIBS_tmp_int = BPCOIEC.EXR_CODE.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCOIEC.EXR_CODE += " ";
        if (!BPCOIEC.EXR_CODE.substring(0, 3).equalsIgnoreCase(WS_EXR_CODE.WS_CCY1) 
            || !BPCOIEC.EXR_CODE.substring(4 - 1, 4 + 3 - 1).equalsIgnoreCase(WS_EXR_CODE.WS_CCY2)) {
            CEP.TRC(SCCGWA, "DEVSOS2");
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INVALID_EXR_CODE, BPCOIEC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_CHECK_CCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = WS_CCY;
        IBS.CALLCPN(SCCGWA, CPN_INQUIRE_CCY, BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCOIEC.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, BPCQCCY.DATA.EXH_FLG);
            if (BPCQCCY.DATA.EXH_FLG != '0') {
                CEP.TRC(SCCGWA, "NOCHANGE");
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CCY_NOT_ALLOW_EX, BPCOIEC.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_BPZPRMB() throws IOException,SQLException,Exception {
        BPCPRMB.DAT_PTR = BPREXSEQ;
        IBS.CALLCPN(SCCGWA, CPN_BRW_PUB_PARM, BPCPRMB);
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ        ", BPCPRMR);
        CEP.TRC(SCCGWA, "DEVHZ467");
        CEP.TRC(SCCGWA, BPCPRMR.RC);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCOIEC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "DEVHZ473");
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
