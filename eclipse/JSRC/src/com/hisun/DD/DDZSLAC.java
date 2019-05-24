package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSLAC {
    int JIBS_tmp_int;
    DBParm DDTMST_RD;
    DBParm DDTVCH_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_FMT_P_INF = "DD909";
    short K_MAX_ROW = 20;
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    short WS_I = 0;
    short WS_CNT = 0;
    int WS_P_ROW = 0;
    int WS_L_CNT = 0;
    int WS_L_ROW = 0;
    int WS_T_PAGE = 0;
    int WS_P_NUM = 0;
    int WS_STR_NUM = 0;
    int WS_END_NUM = 0;
    short WS_NUM1 = 0;
    char WS_VCH_FLG = ' ';
    String WS_REL_AC = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    CICQCIAC CICQCIAC = new CICQCIAC();
    DDRCCY DDRCCY = new DDRCCY();
    DDRMST DDRMST = new DDRMST();
    DDCOLAC DDCOLAC = new DDCOLAC();
    CICQACRL CICQACRL = new CICQACRL();
    CICQACAC CICQACAC = new CICQACAC();
    BPCPOCAC BPCPOCAC = new BPCPOCAC();
    DDRVCH DDRVCH = new DDRVCH();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    DDCSLAC DDCSLAC;
    public void MP(SCCGWA SCCGWA, DDCSLAC DDCSLAC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSLAC = DDCSLAC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSLAC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "11111");
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "22222");
        CEP.TRC(SCCGWA, DDCSLAC.CI_NO);
        CEP.TRC(SCCGWA, DDCSLAC.AC_NO);
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_GET_ACCOUNT_LIST();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSLAC.CI_NO);
        CEP.TRC(SCCGWA, DDCSLAC.AC_NO);
        if (DDCSLAC.CI_NO.trim().length() == 0 
            && DDCSLAC.AC_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CI_AC_MUST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_GET_ACCOUNT_LIST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "33333");
        if (DDCSLAC.AC_NO.trim().length() > 0) {
            B021_GET_ACCOUNT_BY_AC();
            if (pgmRtn) return;
        } else {
            B022_GET_ACCOUNT_BY_CI();
            if (pgmRtn) return;
        }
    }
    public void B021_GET_ACCOUNT_BY_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDCSLAC.AC_NO;
        T000_READ_DDTMST();
        if (pgmRtn) return;
        R000_OUTPUT_INI();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            IBS.init(SCCGWA, DDRVCH);
            DDRVCH.KEY.CUS_AC = DDCSLAC.AC_NO;
            T000_READ_DDTVCH();
            if (pgmRtn) return;
            if (DDRVCH.VCH_TYPE != '2') {
                R000_GET_OPEN_CHNL();
                if (pgmRtn) return;
                R000_DATA_OUTPUT_LIST();
                if (pgmRtn) return;
            }
        }
    }
    public void B022_GET_ACCOUNT_BY_CI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQCIAC);
        CICQCIAC.FUNC = '5';
        CICQCIAC.DATA.CI_NO = DDCSLAC.CI_NO;
        CICQCIAC.DATA.FRM_APP = "DD";
        S000_CALL_CIZQCIAC();
        if (pgmRtn) return;
        R000_OUTPUT_INI();
        if (pgmRtn) return;
        for (WS_CNT = 1; CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_CNT-1].ENTY_NO.trim().length() != 0 
            && WS_CNT <= 99; WS_CNT += 1) {
            CEP.TRC(SCCGWA, CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_CNT-1].ENTY_NO);
            IBS.init(SCCGWA, DDRMST);
            DDRMST.KEY.CUS_AC = CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_CNT-1].ENTY_NO;
            T000_READ_DDTMST();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                IBS.init(SCCGWA, DDRVCH);
                DDRVCH.KEY.CUS_AC = CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_CNT-1].ENTY_NO;
                T000_READ_DDTVCH();
                if (pgmRtn) return;
                if (DDRVCH.VCH_TYPE != '2') {
                    R000_GET_OPEN_CHNL();
                    if (pgmRtn) return;
                    R000_DATA_OUTPUT_LIST();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void R000_GET_OPEN_CHNL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRL);
        CICQACRL.DATA.AC_NO = DDRMST.KEY.CUS_AC;
        CEP.TRC(SCCGWA, DDRVCH.VCH_TYPE);
        if (DDRVCH.VCH_TYPE == '1') {
            CICQACRL.DATA.AC_REL = "09";
        } else {
            if (DDRVCH.VCH_TYPE == '3') {
                CICQACRL.DATA.AC_REL = "13";
            }
        }
        CICQACRL.FUNC = 'I';
        S000_CALL_CIZQACRL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_REL_AC_NO);
        if (CICQACRL.O_DATA.O_REL_AC_NO.trim().length() > 0) {
            WS_REL_AC = CICQACRL.O_DATA.O_REL_AC_NO;
        } else {
            WS_REL_AC = DDRMST.KEY.CUS_AC;
        }
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.DATA.AGR_NO = WS_REL_AC;
        CICQACAC.FUNC = 'R';
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        IBS.init(SCCGWA, BPCPOCAC);
        BPCPOCAC.INFO.FUNC = 'I';
        BPCPOCAC.DATA_INFO.AC = DDRMST.KEY.CUS_AC;
        BPCPOCAC.DATA_INFO.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        S000_CALL_BPZPOCAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPOCAC.DATA_INFO.CHNL_NO);
    }
    public void R000_DATA_OUTPUT_LIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCOLAC);
        DDCOLAC.AC_INFO.AC_NO = DDRMST.KEY.CUS_AC;
        if (DDRMST.CARD_FLG == 'K') {
            DDCOLAC.AC_INFO.BV_TYP = "003";
        } else {
            if (DDRVCH.VCH_TYPE == '1') {
                DDCOLAC.AC_INFO.BV_TYP = "001";
            } else {
                DDCOLAC.AC_INFO.BV_TYP = "002";
            }
        }
        if (DDRVCH.KEY.BV_TYPE == 'B' 
            && DDRMST.CARD_FLG != 'Y') {
            DDCOLAC.AC_INFO.BV_TYP = "004";
        }
        DDCOLAC.AC_INFO.BV_NO = DDRVCH.PSBK_NO;
        DDCOLAC.AC_INFO.PAY_MTH = DDRVCH.PAY_TYPE;
        DDCOLAC.AC_INFO.OPEN_DT = DDRMST.OPEN_DATE;
        DDCOLAC.AC_INFO.OPEN_BR = DDRMST.OPEN_DP;
        DDCOLAC.AC_INFO.OPEN_TLR = DDRMST.OPEN_TLR;
        DDCOLAC.AC_INFO.OPEN_CHNL = BPCPOCAC.DATA_INFO.CHNL_NO;
        DDCOLAC.AC_INFO.CRS_DR_FLG = DDRMST.CROS_DR_FLG;
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("1")) {
            DDCOLAC.AC_INFO.PSBK_STS1 = '1';
        } else {
            DDCOLAC.AC_INFO.PSBK_STS1 = '0';
        }
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
            DDCOLAC.AC_INFO.PSBK_STS2 = '1';
        } else {
            DDCOLAC.AC_INFO.PSBK_STS2 = '0';
        }
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD.substring(6 - 1, 6 + 1 - 1).equalsIgnoreCase("1")) {
            DDCOLAC.AC_INFO.PSBK_STS3 = '1';
        } else {
            DDCOLAC.AC_INFO.PSBK_STS3 = '0';
        }
        DDCOLAC.AC_INFO.AC_STS_WORD = DDRMST.AC_STS_WORD;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, DDCOLAC);
        SCCMPAG.DATA_LEN = 185;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_OUTPUT_INI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 185;
        SCCMPAG.SCR_ROW_CNT = K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        DDTMST_RD.where = "CUS_AC = :DDRMST.KEY.CUS_AC "
            + "AND CI_TYP = '1' "
            + "AND AC_STS = 'N'";
        IBS.READ(SCCGWA, DDRMST, this, DDTMST_RD);
    }
    public void T000_READ_DDTVCH() throws IOException,SQLException,Exception {
        DDTVCH_RD = new DBParm();
        DDTVCH_RD.TableName = "DDTVCH";
        DDTVCH_RD.where = "CUS_AC = :DDRVCH.KEY.CUS_AC";
        IBS.READ(SCCGWA, DDRVCH, this, DDTVCH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_VCH_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_VCH_FLG = 'N';
        }
        CEP.TRC(SCCGWA, WS_VCH_FLG);
    }
    public void S000_CALL_CIZQCIAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST-ACR", CICQCIAC);
    }
    public void S000_CALL_CIZQACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPOCAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-OCAC-INFO", BPCPOCAC, true);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
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
