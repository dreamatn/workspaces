package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;

public class BPZFRWDA {
    boolean pgmRtn = false;
    short WS_CNT = 0;
    String WS_CUS = " ";
    char WS_STSW_FLG = ' ';
    char WS_CUS_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCFRWDA BPCFRWDA;
    BPCGPBDA BPCGPBDA;
    public void MP(SCCGWA SCCGWA, BPCFRWDA BPCFRWDA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCFRWDA = BPCFRWDA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZFRWDA return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCGPBDA = (BPCGPBDA) GWA_BP_AREA.BDA_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCFRWDA);
        if (BPCFRWDA.FUNC == 'A') {
            B010_READ_AC_PROC();
            if (pgmRtn) return;
        } else if (BPCFRWDA.FUNC == 'C') {
            B020_READ_CUS_BY_AC_PROC();
            if (pgmRtn) return;
        } else if (BPCFRWDA.FUNC == 'W') {
            B030_WRITE_PROC();
            if (pgmRtn) return;
        } else if (BPCFRWDA.FUNC == 'S') {
            B040_READ_CUS_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERR, BPCFRWDA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCGPBDA);
    }
    public void B010_READ_AC_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCFRWDA.FUNC);
        CEP.TRC(SCCGWA, BPCFRWDA.INFO.PB_DATA.PB_AC);
        WS_STSW_FLG = 'N';
        for (WS_CNT = 1; WS_CNT <= BPCGPBDA.PB_DATA.PB_CNT 
            && WS_STSW_FLG != 'F'; WS_CNT += 1) {
            CEP.TRC(SCCGWA, BPCGPBDA.PB_DATA.PB_INFO[WS_CNT-1].PB_TYPE);
            CEP.TRC(SCCGWA, BPCGPBDA.PB_DATA.PB_INFO[WS_CNT-1].PB_AC);
            CEP.TRC(SCCGWA, BPCGPBDA.PB_DATA.PB_INFO[WS_CNT-1].PB_CUS);
            CEP.TRC(SCCGWA, BPCGPBDA.PB_DATA.PB_INFO[WS_CNT-1].PB_STSW);
            if (BPCGPBDA.PB_DATA.PB_INFO[WS_CNT-1].PB_TYPE == '2' 
                && BPCGPBDA.PB_DATA.PB_INFO[WS_CNT-1].PB_AC.equalsIgnoreCase(BPCFRWDA.INFO.PB_DATA.PB_AC)) {
                BPCFRWDA.INFO.PB_DATA.PB_STSW = BPCGPBDA.PB_DATA.PB_INFO[WS_CNT-1].PB_STSW;
                WS_STSW_FLG = 'F';
            }
        }
        CEP.TRC(SCCGWA, BPCFRWDA.INFO.PB_DATA.PB_STSW);
        if (WS_STSW_FLG == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_AC_STSW_NOTFND, BPCFRWDA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_READ_CUS_BY_AC_PROC() throws IOException,SQLException,Exception {
        WS_CUS_FLG = 'N';
        for (WS_CNT = 1; WS_CNT <= BPCGPBDA.RE_DATA.RE_CNT 
            && WS_CUS_FLG != 'Y'; WS_CNT += 1) {
            CEP.TRC(SCCGWA, BPCGPBDA.RE_DATA.RE_INFO[WS_CNT-1].RE_TYPE);
            CEP.TRC(SCCGWA, BPCGPBDA.RE_DATA.RE_INFO[WS_CNT-1].RE_CUS);
            CEP.TRC(SCCGWA, BPCGPBDA.RE_DATA.RE_INFO[WS_CNT-1].RE_AC);
            if (BPCGPBDA.RE_DATA.RE_INFO[WS_CNT-1].RE_TYPE == '1' 
                && BPCGPBDA.RE_DATA.RE_INFO[WS_CNT-1].RE_AC.equalsIgnoreCase(BPCFRWDA.INFO.PB_DATA.PB_AC)) {
                WS_CUS = BPCGPBDA.RE_DATA.RE_INFO[WS_CNT-1].RE_CUS;
                WS_CUS_FLG = 'Y';
            }
        }
        CEP.TRC(SCCGWA, WS_CUS);
        if (WS_CUS_FLG == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CUS_OF_AC_NOTFND, BPCFRWDA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_STSW_FLG = 'N';
        for (WS_CNT = 1; WS_CNT <= BPCGPBDA.PB_DATA.PB_CNT 
            && WS_STSW_FLG != 'F'; WS_CNT += 1) {
            CEP.TRC(SCCGWA, BPCGPBDA.PB_DATA.PB_INFO[WS_CNT-1].PB_TYPE);
            CEP.TRC(SCCGWA, BPCGPBDA.PB_DATA.PB_INFO[WS_CNT-1].PB_AC);
            CEP.TRC(SCCGWA, BPCGPBDA.PB_DATA.PB_INFO[WS_CNT-1].PB_CUS);
            CEP.TRC(SCCGWA, BPCGPBDA.PB_DATA.PB_INFO[WS_CNT-1].PB_STSW);
            if (BPCGPBDA.PB_DATA.PB_INFO[WS_CNT-1].PB_TYPE == '1' 
                && BPCGPBDA.PB_DATA.PB_INFO[WS_CNT-1].PB_CUS.equalsIgnoreCase(WS_CUS)) {
                BPCFRWDA.INFO.PB_DATA.PB_STSW = BPCGPBDA.PB_DATA.PB_INFO[WS_CNT-1].PB_STSW;
                WS_STSW_FLG = 'F';
            }
        }
        CEP.TRC(SCCGWA, BPCFRWDA.INFO.PB_DATA.PB_STSW);
        if (WS_STSW_FLG == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CUS_STSW_NOTFND, BPCFRWDA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_WRITE_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCFRWDA.FUNC);
        CEP.TRC(SCCGWA, BPCFRWDA.AREA);
        if (BPCFRWDA.AREA == '1') {
            B031_WRITE_PB_PROC();
            if (pgmRtn) return;
        } else if (BPCFRWDA.AREA == '2') {
            B032_WRITE_RE_PROC();
            if (pgmRtn) return;
        } else if (BPCFRWDA.AREA == '3') {
            B031_WRITE_PB_PROC();
            if (pgmRtn) return;
            B032_WRITE_RE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERR, BPCFRWDA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B031_WRITE_PB_PROC() throws IOException,SQLException,Exception {
        if (BPCGPBDA.PB_DATA.PB_CNT > 29) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PB_DATA_MAX, BPCFRWDA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        for (WS_CNT = 1; WS_CNT <= BPCGPBDA.PB_DATA.PB_CNT; WS_CNT += 1) {
            if (BPCGPBDA.PB_DATA.PB_INFO[WS_CNT-1].PB_TYPE == BPCFRWDA.INFO.PB_DATA.PB_TYPE) {
                if (BPCFRWDA.INFO.PB_DATA.PB_TYPE == '1') {
                    if (BPCGPBDA.PB_DATA.PB_INFO[WS_CNT-1].PB_CUS.equalsIgnoreCase(BPCFRWDA.INFO.PB_DATA.PB_CUS)) {
                        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PB_DATA_EXIST, BPCFRWDA.RC);
                        Z_RET();
                        if (pgmRtn) return;
                    }
                } else if (BPCFRWDA.INFO.PB_DATA.PB_TYPE == '2') {
                    if (BPCGPBDA.PB_DATA.PB_INFO[WS_CNT-1].PB_AC.equalsIgnoreCase(BPCFRWDA.INFO.PB_DATA.PB_AC)) {
                        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PB_DATA_EXIST, BPCFRWDA.RC);
                        Z_RET();
                        if (pgmRtn) return;
                    }
                } else if (BPCFRWDA.INFO.PB_DATA.PB_TYPE == '3') {
                    if (BPCGPBDA.PB_DATA.PB_INFO[WS_CNT-1].PB_CD.equalsIgnoreCase(BPCFRWDA.INFO.PB_DATA.PB_CD)) {
                        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PB_DATA_EXIST, BPCFRWDA.RC);
                        Z_RET();
                        if (pgmRtn) return;
                    }
                } else if (BPCFRWDA.INFO.PB_DATA.PB_TYPE == '4') {
                    if (BPCGPBDA.PB_DATA.PB_INFO[WS_CNT-1].PB_REF.equalsIgnoreCase(BPCFRWDA.INFO.PB_DATA.PB_REF)) {
                        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PB_DATA_EXIST, BPCFRWDA.RC);
                        Z_RET();
                        if (pgmRtn) return;
                    }
                } else {
                }
            }
        }
        BPCGPBDA.PB_DATA.PB_CNT += 1;
        BPCGPBDA.PB_DATA.PB_INFO[BPCGPBDA.PB_DATA.PB_CNT-1].PB_TYPE = BPCFRWDA.INFO.PB_DATA.PB_TYPE;
        if (BPCFRWDA.INFO.PB_DATA.PB_TYPE == '1') {
            BPCGPBDA.PB_DATA.PB_INFO[BPCGPBDA.PB_DATA.PB_CNT-1].PB_CUS = BPCFRWDA.INFO.PB_DATA.PB_CUS;
