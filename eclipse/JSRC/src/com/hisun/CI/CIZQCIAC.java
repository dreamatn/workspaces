package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class CIZQCIAC {
    DBParm CITBAS_RD;
    DBParm CITCDM_RD;
    DBParm CITFDM_RD;
    DBParm CITACR_RD;
    brParm CITACR_BR = new brParm();
    brParm CITACAC_BR = new brParm();
    boolean pgmRtn = false;
    short WS_I = 0;
    String WS_VIL_TYP = " ";
    char WS_COR_AC_FLG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CIRBAS CIRBAS = new CIRBAS();
    CIRCDM CIRCDM = new CIRCDM();
    CIRFDM CIRFDM = new CIRFDM();
    CIRACR CIRACR = new CIRACR();
    CIRACAC CIRACAC = new CIRACAC();
    BPCPQORG BPCPQORG = new BPCPQORG();
    String WS_FRM_APP = " ";
    String WS_CNTRCT_TYP = " ";
    String WS_PROD_CD = " ";
    int WS_OPEN_BR = 0;
    String WS_LAST_ENTY_NO = " ";
    char WS_ACAC_PROV = ' ';
    char WS_CANCEL_INQ_FLG = ' ';
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICQCIAC CICQCIAC;
    public void MP(SCCGWA SCCGWA, CICQCIAC CICQCIAC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICQCIAC = CICQCIAC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZQCIAC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICQCIAC.RC);
        IBS.init(SCCGWA, CICQCIAC.DATA.ACR_AREA);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        if (CICQCIAC.FUNC == '1') {
            B020_INQUIRE_ACR();
            if (pgmRtn) return;
        } else if (CICQCIAC.FUNC == '2') {
            B030_INQUIRE_ACAC();
            if (pgmRtn) return;
        } else if (CICQCIAC.FUNC == '3') {
            B040_INQUIRE_ACAC_BY_AC();
            if (pgmRtn) return;
        } else if (CICQCIAC.FUNC == '4') {
            B050_INQUIRE_CARD();
            if (pgmRtn) return;
        } else if (CICQCIAC.FUNC == '5') {
            B060_INQUIRE_ACR_BY_PRD_OPDT();
            if (pgmRtn) return;
        } else if (CICQCIAC.FUNC == '6') {
            B070_INQUIRE_COR_ACR();
            if (pgmRtn) return;
        } else if (CICQCIAC.FUNC == '7') {
            B080_INQUIRE_ACR_BY_CI();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_FUNC_ERROR, CICQCIAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICQCIAC.DATA.CANCEL_INQ_FLG);
        WS_CANCEL_INQ_FLG = CICQCIAC.DATA.CANCEL_INQ_FLG;
    }
    public void B020_INQUIRE_ACR() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        CEP.TRC(SCCGWA, CICQCIAC.DATA.CI_NO);
        if (CICQCIAC.DATA.CI_NO.trim().length() > 0) {
            IBS.init(SCCGWA, CIRBAS);
            CIRBAS.KEY.CI_NO = CICQCIAC.DATA.CI_NO;
            T000_READ_CITBAS_EXIST();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "CI-NO MUST INPUT");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_NO_MUST_INPUT, CICQCIAC.RC);
            CICQCIAC.DATA.LAST_FLG = 'Y';
            Z_RET();
            if (pgmRtn) return;
        }
    } //FROM #ENDIF
        IBS.init(SCCGWA, CIRACR);
        CIRACR.CI_NO = CICQCIAC.DATA.CI_NO;
        if (CICQCIAC.DATA.LAST_ENTY_NO.trim().length() == 0) {
            T000_STARTBR_CITACR_FIRST();
            if (pgmRtn) return;
        } else {
            T000_STARTBR_CITACR();
            if (pgmRtn) return;
        }
        T000_READNEXT_CITACR();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACR_INF_NOTFND, CICQCIAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_I = 0;
        while (WS_I < 100 
            && SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            WS_I = (short) (WS_I + 1);
            CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_I-1].ENTY_NO = CIRACR.KEY.AGR_NO;
            CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_I-1].ENTY_TYP = CIRACR.ENTY_TYP;
            CEP.TRC(SCCGWA, "------------------------");
            CEP.TRC(SCCGWA, CIRACR.KEY.AGR_NO);
            CEP.TRC(SCCGWA, CIRACR.ENTY_TYP);
            T000_READNEXT_CITACR();
            if (pgmRtn) return;
        }
        CICQCIAC.DATA.LAST_ENTY_NO = CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_I-1].ENTY_NO;
        CICQCIAC.DATA.LAST_ENTY_TYP = CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_I-1].ENTY_TYP;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CICQCIAC.DATA.LAST_FLG = 'Y';
        }
        T000_ENDBR_CITACR();
        if (pgmRtn) return;
    }
    public void B030_INQUIRE_ACAC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICQCIAC.DATA.CI_NO);
        if (CICQCIAC.DATA.CI_NO.trim().length() > 0) {
            IBS.init(SCCGWA, CIRBAS);
            CIRBAS.KEY.CI_NO = CICQCIAC.DATA.CI_NO;
            T000_READ_CITBAS_EXIST();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "CI-NO MUST INPUT");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_NO_MUST_INPUT, CICQCIAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRACAC);
        CIRACAC.CI_NO = CICQCIAC.DATA.CI_NO;
        if (CICQCIAC.DATA.LAST_ENTY_NO.trim().length() == 0) {
            T000_STARTBR_CITACAC_FIRST();
            if (pgmRtn) return;
        } else {
            T000_STARTBR_CITACAC();
            if (pgmRtn) return;
        }
        T000_READNEXT_CITACAC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACAC_INF_NOTFND, CICQCIAC.RC);
            CICQCIAC.DATA.LAST_FLG = 'Y';
            Z_RET();
            if (pgmRtn) return;
        }
        WS_I = 0;
        while (WS_I < 100 
            && SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            WS_I = (short) (WS_I + 1);
            CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_I-1].ENTY_NO = CIRACAC.KEY.ACAC_NO;
            CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_I-1].ENTY_TYP = 'A';
            T000_READNEXT_CITACAC();
            if (pgmRtn) return;
        }
        CICQCIAC.DATA.LAST_ENTY_NO = CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_I-1].ENTY_NO;
        CICQCIAC.DATA.LAST_ENTY_TYP = CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_I-1].ENTY_TYP;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CICQCIAC.DATA.LAST_FLG = 'Y';
        }
        T000_ENDBR_CITACAC();
        if (pgmRtn) return;
    }
    public void B040_INQUIRE_ACAC_BY_AC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICQCIAC.DATA.AGR_NO);
        if (CICQCIAC.DATA.AGR_NO.trim().length() > 0) {
            IBS.init(SCCGWA, CIRACR);
            CIRACR.KEY.AGR_NO = CICQCIAC.DATA.AGR_NO;
            T000_READ_CITACR_EXIST();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "AGR-NO MUST INPUT");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, CICQCIAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRACAC);
        CIRACAC.AGR_NO = CICQCIAC.DATA.AGR_NO;
        if (CICQCIAC.DATA.LAST_ENTY_NO.trim().length() == 0) {
            T000_STARTBR_CITACAC_FIRST2();
            if (pgmRtn) return;
        } else {
            T000_STARTBR_CITACAC2();
            if (pgmRtn) return;
        }
        T000_READNEXT_CITACAC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACAC_INF_NOTFND, CICQCIAC.RC);
            CICQCIAC.DATA.LAST_FLG = 'Y';
            Z_RET();
            if (pgmRtn) return;
        }
        WS_I = 0;
        while (WS_I < 100 
            && SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            WS_I = (short) (WS_I + 1);
            CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_I-1].ENTY_NO = CIRACAC.KEY.ACAC_NO;
            CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_I-1].ENTY_TYP = 'A';
            T000_READNEXT_CITACAC();
            if (pgmRtn) return;
        }
        CICQCIAC.DATA.LAST_ENTY_NO = CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_I-1].ENTY_NO;
        CICQCIAC.DATA.LAST_ENTY_TYP = CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_I-1].ENTY_TYP;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CICQCIAC.DATA.LAST_FLG = 'Y';
        }
        T000_ENDBR_CITACAC();
        if (pgmRtn) return;
    }
    public void B050_INQUIRE_CARD() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        CEP.TRC(SCCGWA, CICQCIAC.DATA.CI_NO);
        if (CICQCIAC.DATA.CI_NO.trim().length() > 0) {
            IBS.init(SCCGWA, CIRBAS);
            CIRBAS.KEY.CI_NO = CICQCIAC.DATA.CI_NO;
            T000_READ_CITBAS_EXIST();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "CI-NO MUST INPUT");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_NO_MUST_INPUT, CICQCIAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, CICQCIAC.DATA.AGR_NO);
        if (CICQCIAC.DATA.AGR_NO.trim().length() > 0) {
            IBS.init(SCCGWA, CIRACR);
            CIRACR.KEY.AGR_NO = CICQCIAC.DATA.AGR_NO;
            T000_READ_CITACR_EXIST();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "AGR-NO MUST INPUT");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, CICQCIAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    } //FROM #ENDIF
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = CICQCIAC.DATA.OPEN_BR;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        WS_VIL_TYP = BPCPQORG.VIL_TYP;
        IBS.init(SCCGWA, CIRACR);
        CIRACR.CI_NO = CICQCIAC.DATA.CI_NO;
        if (CICQCIAC.DATA.LAST_ENTY_NO.trim().length() == 0) {
            T000_STARTBR_CITACR_FIRST_DT();
            if (pgmRtn) return;
        } else {
            T000_STARTBR_CITACR_DT();
            if (pgmRtn) return;
        }
        T000_READNEXT_CITACR();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACR_INF_NOTFND, CICQCIAC.RC);
            CICQCIAC.DATA.LAST_FLG = 'Y';
            Z_RET();
            if (pgmRtn) return;
        }
        WS_I = 0;
        while (WS_I < 100 
            && SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            if (!CIRACR.KEY.AGR_NO.equalsIgnoreCase(CICQCIAC.DATA.AGR_NO)) {
                IBS.init(SCCGWA, BPCPQORG);
                BPCPQORG.BR = CIRACR.OPN_BR;
                S000_CALL_BPZPQORG();
                if (pgmRtn) return;
            }
            if (BPCPQORG.VIL_TYP.equalsIgnoreCase(WS_VIL_TYP) 
                || CIRACR.KEY.AGR_NO.equalsIgnoreCase(CICQCIAC.DATA.AGR_NO)) {
                WS_I = (short) (WS_I + 1);
                CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_I-1].ENTY_NO = CIRACR.KEY.AGR_NO;
                CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_I-1].ENTY_TYP = CIRACR.ENTY_TYP;
            }
            T000_READNEXT_CITACR();
            if (pgmRtn) return;
        }
        CICQCIAC.DATA.LAST_ENTY_NO = CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_I-1].ENTY_NO;
        CICQCIAC.DATA.LAST_ENTY_TYP = CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_I-1].ENTY_TYP;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CICQCIAC.DATA.LAST_FLG = 'Y';
        }
        T000_ENDBR_CITACR();
        if (pgmRtn) return;
    }
    public void B060_INQUIRE_ACR_BY_PRD_OPDT() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        CEP.TRC(SCCGWA, CICQCIAC.DATA.CI_NO);
        if (CICQCIAC.DATA.CI_NO.trim().length() > 0) {
            IBS.init(SCCGWA, CIRBAS);
            CIRBAS.KEY.CI_NO = CICQCIAC.DATA.CI_NO;
            T000_READ_CITBAS_EXIST();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "CI-NO MUST INPUT");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_NO_MUST_INPUT, CICQCIAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    } //FROM #ENDIF
        IBS.init(SCCGWA, CIRACR);
        CIRACR.CI_NO = CICQCIAC.DATA.CI_NO;
        if (CICQCIAC.DATA.LAST_ENTY_NO.trim().length() == 0) {
            T000_STARTBR_CITACR_FIRST_2();
            if (pgmRtn) return;
        } else {
            T000_STARTBR_CITACR_2();
            if (pgmRtn) return;
        }
        T000_READNEXT_CITACR();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACR_INF_NOTFND, CICQCIAC.RC);
            CICQCIAC.DATA.LAST_FLG = 'Y';
            Z_RET();
            if (pgmRtn) return;
        }
        WS_I = 0;
        while (WS_I < 100 
            && SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            WS_I = (short) (WS_I + 1);
            CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_I-1].ENTY_NO = CIRACR.KEY.AGR_NO;
            CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_I-1].ENTY_TYP = CIRACR.ENTY_TYP;
            CEP.TRC(SCCGWA, "------------------------");
            CEP.TRC(SCCGWA, CIRACR.KEY.AGR_NO);
            CEP.TRC(SCCGWA, CIRACR.ENTY_TYP);
            T000_READNEXT_CITACR();
            if (pgmRtn) return;
        }
        CICQCIAC.DATA.LAST_ENTY_NO = CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_I-1].ENTY_NO;
        CICQCIAC.DATA.LAST_ENTY_TYP = CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_I-1].ENTY_TYP;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CICQCIAC.DATA.LAST_FLG = 'Y';
        }
        T000_ENDBR_CITACR();
        if (pgmRtn) return;
    }
    public void B070_INQUIRE_COR_ACR() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        CEP.TRC(SCCGWA, CICQCIAC.DATA.CI_NO);
        if (CICQCIAC.DATA.CI_NO.trim().length() > 0) {
            IBS.init(SCCGWA, CIRBAS);
            CIRBAS.KEY.CI_NO = CICQCIAC.DATA.CI_NO;
            T000_READ_CITBAS_EXIST();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "CI-NO MUST INPUT");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_NO_MUST_INPUT, CICQCIAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, CICQCIAC.DATA.OPEN_BR);
        if (CICQCIAC.DATA.OPEN_BR == 0) {
            CEP.TRC(SCCGWA, "BR MUST INPUT");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_BR_INPUT_ERR, CICQCIAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (CIRBAS.CI_ATTR == '1') {
            WS_COR_AC_FLG = 'N';
            IBS.init(SCCGWA, CIRCDM);
            CIRCDM.KEY.CI_NO = CICQCIAC.DATA.CI_NO;
            T000_READ_CITCDM_EXIST();
            if (pgmRtn) return;
            if (WS_COR_AC_FLG == 'N') {
                IBS.init(SCCGWA, CIRFDM);
                CIRFDM.KEY.CI_NO = CICQCIAC.DATA.CI_NO;
                T000_READ_CITFDM_EXIST();
                if (pgmRtn) return;
            }
        }
    } //FROM #ENDIF
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = CICQCIAC.DATA.OPEN_BR;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        WS_VIL_TYP = BPCPQORG.VIL_TYP;
        CEP.TRC(SCCGWA, WS_VIL_TYP);
        IBS.init(SCCGWA, CIRACR);
        CIRACR.CI_NO = CICQCIAC.DATA.CI_NO;
        if (CICQCIAC.DATA.LAST_ENTY_NO.trim().length() == 0) {
            T000_STARTBR_CITACR_FIRST_3();
            if (pgmRtn) return;
        } else {
            T000_STARTBR_CITACR_3();
            if (pgmRtn) return;
        }
        T000_READNEXT_CITACR();
        if (pgmRtn) return;
        WS_I = 0;
        while (WS_I < 100 
            && SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = CIRACR.OPN_BR;
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
            if (BPCPQORG.VIL_TYP.equalsIgnoreCase(WS_VIL_TYP)) {
                WS_I = (short) (WS_I + 1);
                CEP.TRC(SCCGWA, CIRACR.KEY.AGR_NO);
                CEP.TRC(SCCGWA, CIRACR.ENTY_TYP);
                CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_I-1].ENTY_NO = CIRACR.KEY.AGR_NO;
                CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_I-1].ENTY_TYP = CIRACR.ENTY_TYP;
            }
            T000_READNEXT_CITACR();
            if (pgmRtn) return;
        }
        if (WS_I > 0) {
            CICQCIAC.DATA.LAST_ENTY_NO = CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_I-1].ENTY_NO;
            CICQCIAC.DATA.LAST_ENTY_TYP = CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_I-1].ENTY_TYP;
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CICQCIAC.DATA.LAST_FLG = 'Y';
        }
        T000_ENDBR_CITACR();
        if (pgmRtn) return;
    }
    public void B080_INQUIRE_ACR_BY_CI() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        CEP.TRC(SCCGWA, CICQCIAC.DATA.CI_NO);
        if (CICQCIAC.DATA.CI_NO.trim().length() > 0) {
            IBS.init(SCCGWA, CIRBAS);
            CIRBAS.KEY.CI_NO = CICQCIAC.DATA.CI_NO;
            T000_READ_CITBAS_EXIST();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "CI-NO MUST INPUT");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_NO_MUST_INPUT, CICQCIAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    } //FROM #ENDIF
        IBS.init(SCCGWA, CIRACR);
        CIRACR.CI_NO = CICQCIAC.DATA.CI_NO;
        if (CICQCIAC.DATA.LAST_ENTY_NO.trim().length() == 0) {
            T000_STARTBR_CITACR_FIRST_4();
            if (pgmRtn) return;
        } else {
            T000_STARTBR_CITACR_4();
            if (pgmRtn) return;
        }
        T000_READNEXT_CITACR();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACR_INF_NOTFND, CICQCIAC.RC);
            CICQCIAC.DATA.LAST_FLG = 'Y';
            Z_RET();
            if (pgmRtn) return;
        }
        WS_I = 0;
        while (WS_I < 100 
            && SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            WS_I = (short) (WS_I + 1);
            CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_I-1].ENTY_NO = CIRACR.KEY.AGR_NO;
            CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_I-1].ENTY_TYP = CIRACR.ENTY_TYP;
            CEP.TRC(SCCGWA, "------------------------");
            CEP.TRC(SCCGWA, CIRACR.KEY.AGR_NO);
            CEP.TRC(SCCGWA, CIRACR.ENTY_TYP);
            T000_READNEXT_CITACR();
            if (pgmRtn) return;
        }
        CICQCIAC.DATA.LAST_ENTY_NO = CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_I-1].ENTY_NO;
        CICQCIAC.DATA.LAST_ENTY_TYP = CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_I-1].ENTY_TYP;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CICQCIAC.DATA.LAST_FLG = 'Y';
        }
        T000_ENDBR_CITACR();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPQORG.RC);
        }
    }
    public void T000_READ_CITBAS_EXIST() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_RECORD_NOTFND, CICQCIAC.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITBAS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITCDM_EXIST() throws IOException,SQLException,Exception {
        CITCDM_RD = new DBParm();
        CITCDM_RD.TableName = "CITCDM";
        IBS.READ(SCCGWA, CIRCDM, CITCDM_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COR_AC_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITCDM";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITFDM_EXIST() throws IOException,SQLException,Exception {
        CITFDM_RD = new DBParm();
        CITFDM_RD.TableName = "CITFDM";
        IBS.READ(SCCGWA, CIRFDM, CITFDM_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_RECORD_NOTFND, CICQCIAC.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITFDM";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITACR_EXIST() throws IOException,SQLException,Exception {
        CITACR_RD = new DBParm();
        CITACR_RD.TableName = "CITACR";
        IBS.READ(SCCGWA, CIRACR, CITACR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACR_INF_NOTFND, CICQCIAC.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITACR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_CITACR_FIRST() throws IOException,SQLException,Exception {
        WS_FRM_APP = CICQCIAC.DATA.FRM_APP;
        WS_CNTRCT_TYP = CICQCIAC.DATA.CNTRCT_TYP;
        WS_PROD_CD = CICQCIAC.DATA.PROD_CD;
        WS_OPEN_BR = CICQCIAC.DATA.OPEN_BR;
        CITACR_BR.rp = new DBParm();
        CITACR_BR.rp.TableName = "CITACR";
        CITACR_BR.rp.eqWhere = "CI_NO";
        CITACR_BR.rp.where = "( FRM_APP = :WS_FRM_APP "
            + "OR :WS_FRM_APP = ' ' ) "
            + "AND ( CNTRCT_TYP = :WS_CNTRCT_TYP "
            + "OR :WS_CNTRCT_TYP = ' ' ) "
            + "AND ( PROD_CD = :WS_PROD_CD "
            + "OR :WS_PROD_CD = ' ' ) "
            + "AND ( OPN_BR = :WS_OPEN_BR "
            + "OR :WS_OPEN_BR = 0 ) "
            + "AND ( STS = '0' "
            + "OR :WS_CANCEL_INQ_FLG = 'Y' )";
        CITACR_BR.rp.order = "AGR_NO";
        IBS.STARTBR(SCCGWA, CIRACR, this, CITACR_BR);
    }
    public void T000_STARTBR_CITACR() throws IOException,SQLException,Exception {
        WS_FRM_APP = CICQCIAC.DATA.FRM_APP;
        WS_CNTRCT_TYP = CICQCIAC.DATA.CNTRCT_TYP;
        WS_PROD_CD = CICQCIAC.DATA.PROD_CD;
        WS_LAST_ENTY_NO = CICQCIAC.DATA.LAST_ENTY_NO;
        WS_OPEN_BR = CICQCIAC.DATA.OPEN_BR;
        CITACR_BR.rp = new DBParm();
        CITACR_BR.rp.TableName = "CITACR";
        CITACR_BR.rp.eqWhere = "CI_NO";
        CITACR_BR.rp.where = "( FRM_APP = :WS_FRM_APP "
            + "OR :WS_FRM_APP = ' ' ) "
            + "AND ( CNTRCT_TYP = :WS_CNTRCT_TYP "
            + "OR :WS_CNTRCT_TYP = ' ' ) "
            + "AND ( PROD_CD = :WS_PROD_CD "
            + "OR :WS_PROD_CD = ' ' ) "
            + "AND AGR_NO > :WS_LAST_ENTY_NO "
            + "AND ( OPN_BR = :WS_OPEN_BR "
            + "OR :WS_OPEN_BR = 0 ) "
            + "AND ( STS = '0' "
            + "OR :WS_CANCEL_INQ_FLG = 'Y' )";
        CITACR_BR.rp.order = "AGR_NO";
        IBS.STARTBR(SCCGWA, CIRACR, this, CITACR_BR);
    }
    public void T000_STARTBR_CITACR_FIRST_DT() throws IOException,SQLException,Exception {
        CITACR_BR.rp = new DBParm();
        CITACR_BR.rp.TableName = "CITACR";
        CITACR_BR.rp.eqWhere = "CI_NO";
        CITACR_BR.rp.where = "STS = '0' "
            + "AND ENTY_TYP = '2'";
        CITACR_BR.rp.order = "OPEN_DT,AGR_NO";
        IBS.STARTBR(SCCGWA, CIRACR, this, CITACR_BR);
    }
    public void T000_STARTBR_CITACR_DT() throws IOException,SQLException,Exception {
        WS_LAST_ENTY_NO = CICQCIAC.DATA.LAST_ENTY_NO;
        CITACR_BR.rp = new DBParm();
        CITACR_BR.rp.TableName = "CITACR";
        CITACR_BR.rp.eqWhere = "CI_NO";
        CITACR_BR.rp.where = "AGR_NO > :WS_LAST_ENTY_NO "
            + "AND STS = '0' "
            + "AND ENTY_TYP = '2'";
        CITACR_BR.rp.order = "OPEN_DT,AGR_NO";
        IBS.STARTBR(SCCGWA, CIRACR, this, CITACR_BR);
    }
    public void T000_STARTBR_CITACR_FIRST_2() throws IOException,SQLException,Exception {
        WS_FRM_APP = CICQCIAC.DATA.FRM_APP;
        WS_CNTRCT_TYP = CICQCIAC.DATA.CNTRCT_TYP;
        WS_PROD_CD = CICQCIAC.DATA.PROD_CD;
        WS_OPEN_BR = CICQCIAC.DATA.OPEN_BR;
        CITACR_BR.rp = new DBParm();
        CITACR_BR.rp.TableName = "CITACR";
        CITACR_BR.rp.eqWhere = "CI_NO";
        CITACR_BR.rp.where = "( FRM_APP = :WS_FRM_APP "
            + "OR :WS_FRM_APP = ' ' ) "
            + "AND ( CNTRCT_TYP = :WS_CNTRCT_TYP "
            + "OR :WS_CNTRCT_TYP = ' ' ) "
            + "AND ( PROD_CD = :WS_PROD_CD "
            + "OR :WS_PROD_CD = ' ' ) "
            + "AND ( OPN_BR = :WS_OPEN_BR "
            + "OR :WS_OPEN_BR = 0 ) "
            + "AND ( STS = '0' "
            + "OR :WS_CANCEL_INQ_FLG = 'Y' )";
        CITACR_BR.rp.order = "PROD_CD,OPEN_DT,AGR_NO";
        IBS.STARTBR(SCCGWA, CIRACR, this, CITACR_BR);
    }
    public void T000_STARTBR_CITACR_2() throws IOException,SQLException,Exception {
        WS_FRM_APP = CICQCIAC.DATA.FRM_APP;
        WS_CNTRCT_TYP = CICQCIAC.DATA.CNTRCT_TYP;
        WS_PROD_CD = CICQCIAC.DATA.PROD_CD;
        WS_LAST_ENTY_NO = CICQCIAC.DATA.LAST_ENTY_NO;
        WS_OPEN_BR = CICQCIAC.DATA.OPEN_BR;
        CITACR_BR.rp = new DBParm();
        CITACR_BR.rp.TableName = "CITACR";
        CITACR_BR.rp.eqWhere = "CI_NO";
        CITACR_BR.rp.where = "( FRM_APP = :WS_FRM_APP "
            + "OR :WS_FRM_APP = ' ' ) "
            + "AND ( CNTRCT_TYP = :WS_CNTRCT_TYP "
            + "OR :WS_CNTRCT_TYP = ' ' ) "
            + "AND ( PROD_CD = :WS_PROD_CD "
            + "OR :WS_PROD_CD = ' ' ) "
            + "AND AGR_NO > :WS_LAST_ENTY_NO "
            + "AND ( OPN_BR = :WS_OPEN_BR "
            + "OR :WS_OPEN_BR = 0 ) "
            + "AND ( STS = '0' "
            + "OR :WS_CANCEL_INQ_FLG = 'Y' )";
        CITACR_BR.rp.order = "PROD_CD,OPEN_DT,AGR_NO";
        IBS.STARTBR(SCCGWA, CIRACR, this, CITACR_BR);
    }
    public void T000_STARTBR_CITACR_FIRST_3() throws IOException,SQLException,Exception {
        CITACR_BR.rp = new DBParm();
        CITACR_BR.rp.TableName = "CITACR";
        CITACR_BR.rp.eqWhere = "CI_NO";
        CITACR_BR.rp.where = "( STS = '0' "
            + "OR :WS_CANCEL_INQ_FLG = 'Y' ) "
            + "AND ENTY_TYP < > '4' "
            + "AND PROD_CD = '2206030101' "
            + "AND CCY = '156' "
            + "AND FRM_APP = 'DD'";
        CITACR_BR.rp.order = "OPEN_DT DESC,AGR_NO";
        IBS.STARTBR(SCCGWA, CIRACR, this, CITACR_BR);
    }
    public void T000_STARTBR_CITACR_3() throws IOException,SQLException,Exception {
        WS_LAST_ENTY_NO = CICQCIAC.DATA.LAST_ENTY_NO;
        CITACR_BR.rp = new DBParm();
        CITACR_BR.rp.TableName = "CITACR";
        CITACR_BR.rp.eqWhere = "CI_NO";
        CITACR_BR.rp.where = "AGR_NO > :WS_LAST_ENTY_NO "
            + "AND ( STS = '0' "
            + "OR WS_CANCEL_INQ_FLG = 'Y' ) "
            + "AND ENTY_TYP < > '4' "
            + "AND PROD_CD = '2206030101' "
            + "AND CCY = '156' "
            + "AND FRM_APP = 'DD'";
        CITACR_BR.rp.order = "OPEN_DT DESC,AGR_NO";
        IBS.STARTBR(SCCGWA, CIRACR, this, CITACR_BR);
    }
    public void T000_STARTBR_CITACR_FIRST_4() throws IOException,SQLException,Exception {
        CITACR_BR.rp = new DBParm();
        CITACR_BR.rp.TableName = "CITACR";
        CITACR_BR.rp.eqWhere = "CI_NO";
        CITACR_BR.rp.where = "( STS = '0' "
            + "OR :WS_CANCEL_INQ_FLG = 'Y' ) "
            + "AND FRM_APP IN ( 'DD' , 'DC' )";
        CITACR_BR.rp.order = "OPEN_DT DESC,AGR_NO";
        IBS.STARTBR(SCCGWA, CIRACR, this, CITACR_BR);
    }
    public void T000_STARTBR_CITACR_4() throws IOException,SQLException,Exception {
        CITACR_BR.rp = new DBParm();
        CITACR_BR.rp.TableName = "CITACR";
        CITACR_BR.rp.eqWhere = "CI_NO";
        CITACR_BR.rp.where = "AGR_NO > :WS_LAST_ENTY_NO "
            + "AND ( STS = '0' "
            + "OR :WS_CANCEL_INQ_FLG = 'Y' ) "
            + "AND FRM_APP IN ( 'DD' , 'DC' )";
        CITACR_BR.rp.order = "OPEN_DT DESC,AGR_NO";
        IBS.STARTBR(SCCGWA, CIRACR, this, CITACR_BR);
    }
    public void T000_READNEXT_CITACR() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRACR, this, CITACR_BR);
    }
    public void T000_ENDBR_CITACR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITACR_BR);
    }
    public void T000_STARTBR_CITACAC_FIRST() throws IOException,SQLException,Exception {
        WS_ACAC_PROV = CICQCIAC.DATA.ACAC_PROV;
        WS_FRM_APP = CICQCIAC.DATA.FRM_APP;
        WS_OPEN_BR = CICQCIAC.DATA.OPEN_BR;
        WS_PROD_CD = CICQCIAC.DATA.PROD_CD;
        CITACAC_BR.rp = new DBParm();
        CITACAC_BR.rp.TableName = "CITACAC";
        CITACAC_BR.rp.eqWhere = "CI_NO";
        CITACAC_BR.rp.where = "( SUBSTR ( ACAC_CTL , 4 , 1 ) = '1' "
            + "OR :WS_ACAC_PROV < > 'Y' ) "
            + "AND ( FRM_APP = :WS_FRM_APP "
            + "OR :WS_FRM_APP = ' ' ) "
            + "AND ( OPN_BR = :WS_OPEN_BR "
            + "OR :WS_OPEN_BR = 0 ) "
            + "AND ( PROD_CD = :WS_PROD_CD "
            + "OR :WS_PROD_CD = ' ' ) "
            + "AND ( ACAC_STS = '0' "
            + "OR :WS_CANCEL_INQ_FLG = 'Y' )";
        CITACAC_BR.rp.order = "OPEN_DT,ACAC_NO";
        IBS.STARTBR(SCCGWA, CIRACAC, this, CITACAC_BR);
    }
    public void T000_STARTBR_CITACAC() throws IOException,SQLException,Exception {
        WS_ACAC_PROV = CICQCIAC.DATA.ACAC_PROV;
        WS_FRM_APP = CICQCIAC.DATA.FRM_APP;
        WS_LAST_ENTY_NO = CICQCIAC.DATA.LAST_ENTY_NO;
        WS_OPEN_BR = CICQCIAC.DATA.OPEN_BR;
        WS_PROD_CD = CICQCIAC.DATA.PROD_CD;
        CITACAC_BR.rp = new DBParm();
        CITACAC_BR.rp.TableName = "CITACAC";
        CITACAC_BR.rp.eqWhere = "CI_NO";
        CITACAC_BR.rp.where = "( SUBSTR ( ACAC_CTL , 4 , 1 ) = '1' "
            + "OR :WS_ACAC_PROV < > 'Y' ) "
            + "AND ( FRM_APP = :WS_FRM_APP "
            + "OR :WS_FRM_APP = ' ' ) "
            + "AND ACAC_NO > :WS_LAST_ENTY_NO "
            + "AND ( OPN_BR = :WS_OPEN_BR "
            + "OR :WS_OPEN_BR = 0 ) "
            + "AND ( PROD_CD = :WS_PROD_CD "
            + "OR :WS_PROD_CD = ' ' ) "
            + "AND ( ACAC_STS = '0' "
            + "OR :WS_CANCEL_INQ_FLG = 'Y' )";
        CITACAC_BR.rp.order = "OPEN_DT,ACAC_NO";
        IBS.STARTBR(SCCGWA, CIRACAC, this, CITACAC_BR);
    }
    public void T000_STARTBR_CITACAC_FIRST2() throws IOException,SQLException,Exception {
        WS_ACAC_PROV = CICQCIAC.DATA.ACAC_PROV;
        WS_FRM_APP = CICQCIAC.DATA.FRM_APP;
        WS_OPEN_BR = CICQCIAC.DATA.OPEN_BR;
        WS_PROD_CD = CICQCIAC.DATA.PROD_CD;
        CITACAC_BR.rp = new DBParm();
        CITACAC_BR.rp.TableName = "CITACAC";
        CITACAC_BR.rp.eqWhere = "AGR_NO";
        CITACAC_BR.rp.where = "( SUBSTR ( ACAC_CTL , 4 , 1 ) = '1' "
            + "OR :WS_ACAC_PROV < > 'Y' ) "
            + "AND ( FRM_APP = :WS_FRM_APP "
            + "OR :WS_FRM_APP = ' ' ) "
            + "AND ( OPN_BR = :WS_OPEN_BR "
            + "OR :WS_OPEN_BR = 0 ) "
            + "AND ( PROD_CD = :WS_PROD_CD "
            + "OR :WS_PROD_CD = ' ' ) "
            + "AND ( ACAC_STS = '0' "
            + "OR :WS_CANCEL_INQ_FLG = 'Y' )";
        CITACAC_BR.rp.order = "ACAC_NO";
        IBS.STARTBR(SCCGWA, CIRACAC, this, CITACAC_BR);
    }
    public void T000_STARTBR_CITACAC2() throws IOException,SQLException,Exception {
        WS_ACAC_PROV = CICQCIAC.DATA.ACAC_PROV;
        WS_FRM_APP = CICQCIAC.DATA.FRM_APP;
        WS_LAST_ENTY_NO = CICQCIAC.DATA.LAST_ENTY_NO;
        WS_OPEN_BR = CICQCIAC.DATA.OPEN_BR;
        WS_PROD_CD = CICQCIAC.DATA.PROD_CD;
        CITACAC_BR.rp = new DBParm();
        CITACAC_BR.rp.TableName = "CITACAC";
        CITACAC_BR.rp.eqWhere = "AGR_NO";
        CITACAC_BR.rp.where = "( SUBSTR ( ACAC_CTL , 4 , 1 ) = '1' "
            + "OR :WS_ACAC_PROV < > 'Y' ) "
            + "AND ( FRM_APP = :WS_FRM_APP "
            + "OR :WS_FRM_APP = ' ' ) "
            + "AND ACAC_NO > :WS_LAST_ENTY_NO "
            + "AND ( OPN_BR = :WS_OPEN_BR "
            + "OR :WS_OPEN_BR = 0 ) "
            + "AND ( PROD_CD = :WS_PROD_CD "
            + "OR :WS_PROD_CD = ' ' ) "
            + "AND ( ACAC_STS = '0' "
            + "OR :WS_CANCEL_INQ_FLG = 'Y' )";
        CITACAC_BR.rp.order = "ACAC_NO";
        IBS.STARTBR(SCCGWA, CIRACAC, this, CITACAC_BR);
    }
    public void T000_READNEXT_CITACAC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRACAC, this, CITACAC_BR);
    }
    public void T000_ENDBR_CITACAC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITACAC_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
