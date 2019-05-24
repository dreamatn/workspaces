package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class DCZICCYA {
    String JIBS_tmp_str[] = new String[10];
    DBParm DCTIACCY_RD;
    boolean pgmRtn = false;
    String K_AP_MMO = "DC";
    char WS_IACCY_REC_FLG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    DCRIACCY DCRIACCY = new DCRIACCY();
    BPCQCCY BPCQCCY = new BPCQCCY();
    SCCGWA SCCGWA;
    DCCICCYA DCCICCYA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA, DCCICCYA DCCICCYA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCICCYA = DCCICCYA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZICCYA return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.CPY2CLS(SCCGWA, "DC0000", DCCICCYA.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_CHECK_CCY_PROC();
        if (pgmRtn) return;
        B030_CRT_UPD_CCY_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DCCICCYA.VIA_AC.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_VIA_AC_MUST_INPUT, DCCICCYA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DCCICCYA.CCY.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CCY_M_INPUT, DCCICCYA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DCCICCYA.CCY_TYPE != '1' 
            && DCCICCYA.CCY_TYPE != '2') {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CCY_TYP_INVALID, DCCICCYA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DCCICCYA.CCY.equalsIgnoreCase("156") 
            && DCCICCYA.CCY_TYPE == '2') {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CCY_TYP_INVALID, DCCICCYA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DCCICCYA.TRS_AMT < 0) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_TRS_AMT_INVALID, DCCICCYA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_CHECK_CCY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = DCCICCYA.CCY;
        S000_CALL_BPZQCCY();
        if (pgmRtn) return;
        if (BPCQCCY.RC.RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCICCYA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_CRT_UPD_CCY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRIACCY);
        DCRIACCY.KEY.VIA_AC = DCCICCYA.VIA_AC;
        DCRIACCY.KEY.CCY = DCCICCYA.CCY;
        DCRIACCY.KEY.CCY_TYPE = DCCICCYA.CCY_TYPE;
        T00_READ_DCTIACCY_R();
        if (pgmRtn) return;
        if (WS_IACCY_REC_FLG == 'N') {
            R000_CRT_CCY_PROC();
            if (pgmRtn) return;
        } else {
            pgmRtn = true;
            return;
        }
    }
    public void R000_CRT_CCY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRIACCY);
        DCRIACCY.KEY.VIA_AC = DCCICCYA.VIA_AC;
        DCRIACCY.KEY.CCY = DCCICCYA.CCY;
        DCRIACCY.KEY.CCY_TYPE = DCCICCYA.CCY_TYPE;
        DCRIACCY.DD_TOT_BAL = DCCICCYA.TRS_AMT;
        DCRIACCY.LAST_DD_BAL = 0;
        DCRIACCY.LAST_DD_ACDT = 0;
        DCRIACCY.TD_TOT_BAL = 0;
        DCRIACCY.LAST_TD_BAL = 0;
        DCRIACCY.HLD_MTH = ' ';
        DCRIACCY.TOT_HLD_BAL = 0;
        DCRIACCY.DD_HLD_BAL = 0;
        DCRIACCY.TD_HLD_BAL = 0;
        DCRIACCY.AMT1 = 0;
        DCRIACCY.AMT2 = 0;
        DCRIACCY.AMT3 = 0;
        DCRIACCY.AMT4 = 0;
        DCRIACCY.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRIACCY.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_DCTIACCY();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
    }
    public void T00_READ_DCTIACCY_R() throws IOException,SQLException,Exception {
        DCTIACCY_RD = new DBParm();
        DCTIACCY_RD.TableName = "DCTIACCY";
        IBS.READ(SCCGWA, DCRIACCY, DCTIACCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_IACCY_REC_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_IACCY_REC_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "READ TABLE DCTIACCY ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTIACCY";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_DCTIACCY() throws IOException,SQLException,Exception {
        DCTIACCY_RD = new DBParm();
        DCTIACCY_RD.TableName = "DCTIACCY";
        IBS.WRITE(SCCGWA, DCRIACCY, DCTIACCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_IA_CCY_RCD_ALR_EXS, DCCICCYA.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTIACCY";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
