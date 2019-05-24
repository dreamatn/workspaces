package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPQCAL {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY      ";
    String CPN_P_QUERY_BANK = "BP-P-QUERY-BANK     ";
    String CPN_P_INQ_RGNC = "BP-P-INQ-RGNC       ";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG        ";
    String CPN_CALL_BPZQCNCI = "BP-Q-CNTY-CITY-IFO  ";
    BPZPQCAL_WS_QCAL_RC WS_QCAL_RC = new BPZPQCAL_WS_QCAL_RC();
    short WS_D = 0;
    char WS_FND = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCPQRGC BPCPQRGC = new BPCPQRGC();
    BPCPQBNK BPCPQBNK = new BPCPQBNK();
    BPCQCNCI BPCQCNCI = new BPCQCNCI();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCOQCAL BPCOQCAL;
    public void MP(SCCGWA SCCGWA, BPCOQCAL BPCOQCAL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCOQCAL = BPCOQCAL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPQCAL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, BPCOQCAL.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_INQ_CAL_CODE();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (BPCOQCAL.BK.compareTo(SPACE) <= 0) {
            BPCOQCAL.BK = SCCGWA.COMM_AREA.TR_BANK;
        }
        if (BPCOQCAL.BK.compareTo(SPACE) <= 0) {
            CEP.TRC(SCCGWA, "BK");
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCOQCAL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_INQ_CAL_CODE() throws IOException,SQLException,Exception {
        WS_FND = 'N';
        if (BPCOQCAL.FUNC == '1') {
            B021_BY_CCY();
            if (pgmRtn) return;
        } else if (BPCOQCAL.FUNC == '2') {
            B022_BY_BR();
            if (pgmRtn) return;
        } else if (BPCOQCAL.FUNC == '3') {
            B023_BY_RGN();
            if (pgmRtn) return;
        } else if (BPCOQCAL.FUNC == '4') {
            B023_BY_RGN();
            if (pgmRtn) return;
            if (WS_FND == 'N') {
                B024_BY_BK();
                if (pgmRtn) return;
            }
        } else if (BPCOQCAL.FUNC == '5') {
            B022_BY_BR();
            if (pgmRtn) return;
            if (WS_FND == 'N') {
                B024_BY_BK();
                if (pgmRtn) return;
            }
        } else if (BPCOQCAL.FUNC == '6') {
            B022_BY_BR();
            if (pgmRtn) return;
            if (WS_FND == 'N') {
                B023_BY_RGN();
                if (pgmRtn) return;
            }
            if (WS_FND == 'N') {
                B024_BY_BK();
                if (pgmRtn) return;
            }
        } else if (BPCOQCAL.FUNC == '7') {
            B024_BY_BK();
            if (pgmRtn) return;
        } else if (BPCOQCAL.FUNC == '8') {
            B024_BY_BK();
            if (pgmRtn) return;
        } else if (BPCOQCAL.FUNC == '9') {
            B025_BY_CITY();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "OTHER");
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCOQCAL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (WS_FND == 'N') {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_QCAL_RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCOQCAL.RC);
        }
    }
    public void B021_BY_CCY() throws IOException,SQLException,Exception {
        if (BPCOQCAL.CCY.compareTo(SPACE) <= 0) {
            CEP.TRC(SCCGWA, "CCY");
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCOQCAL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = BPCOQCAL.CCY;
        S000_CALL_BPZQCCY();
        if (pgmRtn) return;
    }
    public void B022_BY_BR() throws IOException,SQLException,Exception {
        if (BPCOQCAL.BR <= 0) {
            CEP.TRC(SCCGWA, "BR");
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCOQCAL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BNK = BPCOQCAL.BK;
        BPCPQORG.BR = BPCOQCAL.BR;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
    }
    public void B023_BY_RGN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQRGC);
        BPCPQRGC.RGN_NO.RGN_TYPE = BPCOQCAL.RGN_NO.RGN_TYPE;
        BPCPQRGC.RGN_NO.RGN_SEQ = BPCOQCAL.RGN_NO.RGN_SEQ;
        S000_CALL_BPZPQRGC();
        if (pgmRtn) return;
    }
    public void B024_BY_BK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQBNK);
        BPCPQBNK.DATA_INFO.BNK = BPCOQCAL.BK;
        CEP.TRC(SCCGWA, BPCOQCAL.BK);
        CEP.TRC(SCCGWA, BPCPQBNK.DATA_INFO.BNK);
        S000_CALL_BPZPQBNK();
        if (pgmRtn) return;
    }
    public void B025_BY_CITY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCNCI);
        BPCQCNCI.INPUT_DAT.CNTY_CD = BPCOQCAL.CITY_NO.CNTYS_CD;
        BPCQCNCI.INPUT_DAT.CITY_CD = BPCOQCAL.CITY_NO.CITY_CD;
        S000_CALL_BPZQCNCI();
        if (pgmRtn) return;
        if (BPCOQCAL.CITY_NO.CITY_CD.trim().length() == 0) {
            BPCOQCAL.CAL_CODE = BPCQCNCI.OUTPUT_CNTY_DAT.CALR_CODE;
        } else {
            BPCOQCAL.CAL_CODE = BPCQCNCI.OUTPUT_CITY_DAT.I_CAL_CODE;
        }
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
        CEP.TRC(SCCGWA, BPCQCCY.DATA.CAL_CD);
        if (BPCQCCY.RC.RTNCODE == 0) {
            WS_FND = 'Y';
            BPCOQCAL.CAL_CODE = BPCQCCY.DATA.CAL_CD;
        } else {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_QCAL_RC);
        }
    }
    public void S000_CALL_BPZQCNCI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-Q-CNTY-CITY-IFO", BPCQCNCI);
        if (BPCQCNCI.RC.RC_CODE != 0 
            && BPCOQCAL.CITY_NO.CITY_CD.trim().length() > 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCNCI.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_QCAL_RC);
        } else {
            if (BPCQCNCI.RC.RC_CODE != 0 
                && BPCOQCAL.CITY_NO.CITY_CD.trim().length() == 0 
                && BPCQCNCI.FLG != 'Y') {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCNCI.RC);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_QCAL_RC);
            } else {
                WS_FND = 'Y';
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCNCI.RC);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_QCAL_RC);
            }
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE == 0) {
            WS_FND = 'Y';
            BPCOQCAL.CAL_CODE = BPCPQORG.CALD_CD;
        } else {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_QCAL_RC);
        }
    }
    public void S000_CALL_BPZPQRGC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-RGNC", BPCPQRGC);
        if (BPCPQRGC.RC.RC_CODE == 0) {
            WS_FND = 'Y';
            BPCOQCAL.CAL_CODE = BPCPQRGC.CALD_CD;
        } else {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQRGC.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_QCAL_RC);
        }
    }
    public void S000_CALL_BPZPQBNK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-BANK", BPCPQBNK);
        if (BPCPQBNK.RC.RC_CODE == 0) {
            WS_FND = 'Y';
            if (BPCOQCAL.FUNC == '4'
                || BPCOQCAL.FUNC == '5'
                || BPCOQCAL.FUNC == '6'
                || BPCOQCAL.FUNC == '7'
                || BPCOQCAL.FUNC == '8') {
                BPCOQCAL.CAL_CODE = BPCPQBNK.DATA_INFO.CALD_SYS;
            }
        } else {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQBNK.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_QCAL_RC);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCOQCAL.RC);
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCOQCAL.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCOQCAL = ");
            CEP.TRC(SCCGWA, BPCOQCAL);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
