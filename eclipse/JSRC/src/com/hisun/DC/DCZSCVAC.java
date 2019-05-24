package com.hisun.DC;

import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSCVAC {
    DBParm DCTIACLS_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    char WS_RETURN_INFO = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    DCRIACLS DCRIACLS = new DCRIACLS();
    DCCICLSC DCCICLSC = new DCCICLSC();
    DCCIMSTR DCCIMSTR = new DCCIMSTR();
    String WS_SCVAC_VIA_AC = " ";
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCSCVAC DCCSCVAC;
    public void MP(SCCGWA SCCGWA, DCCSCVAC DCCSCVAC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSCVAC = DCCSCVAC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSCVAC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_RTV_IAMST_DATA();
        if (pgmRtn) return;
        B030_CHK_IAMST_INFO();
        if (pgmRtn) return;
        B040_CHK_IACLS_INFO();
        if (pgmRtn) return;
        B050_UPD_IACLS_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DCCSCVAC.VIA_AC.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_VIA_AC_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_RTV_IAMST_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCIMSTR);
        DCCIMSTR.KEY.VIA_AC = DCCSCVAC.VIA_AC;
        S000_CALL_DCZIMSTR();
        if (pgmRtn) return;
    }
    public void B030_CHK_IAMST_INFO() throws IOException,SQLException,Exception {
        if (DCCIMSTR.DATA.AC_STS == 'C') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_VIA_AC_MST_ALR_CLS;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DCCIMSTR.DATA.PRD_TYPE);
        if (!DCCIMSTR.DATA.PRD_TYPE.equalsIgnoreCase("07")) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_VA_PRD_TYPE_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B040_CHK_IACLS_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRIACLS);
        DCRIACLS.KEY.DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRIACLS.KEY.TYPE = '1';
        DCRIACLS.KEY.AC = DCCSCVAC.VIA_AC;
        T000_READ_DCTIACLS();
        if (pgmRtn) return;
        if (WS_RETURN_INFO == 'F') {
            if (DCRIACLS.STS == 'D') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CLS_RCD_ALR_CAN;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CLS_RCD_NOT_FND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B050_UPD_IACLS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCICLSC);
        DCCICLSC.INP_DATA.AC = DCCSCVAC.VIA_AC;
        S000_CALL_DCZICLSC();
        if (pgmRtn) return;
    }
    public void S000_CALL_DCZIMSTR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-I-INQ-IAMST", DCCIMSTR);
        if (DCCIMSTR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCIMSTR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZICLSC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-I-CLS-CAN", DCCICLSC);
        if (DCCICLSC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCICLSC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void T000_READ_DCTIACLS() throws IOException,SQLException,Exception {
        DCTIACLS_RD = new DBParm();
        DCTIACLS_RD.TableName = "DCTIACLS";
        DCTIACLS_RD.errhdl = true;
        IBS.READ(SCCGWA, DCRIACLS, DCTIACLS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_RETURN_INFO = 'N';
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "READ TABLE DCTIACLS ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTIACLS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_C);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_D);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_Q);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_M);
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
