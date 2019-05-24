package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZUCBAL {
    DBParm DDTCCY_RD;
    brParm DDTMST_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    double WS_LIMIT_AMT = 0;
    char WS_TABLE_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPCEX BPCEX = new BPCEX();
    DDCSFGAM DDCSFGAM = new DDCSFGAM();
    DDRMST DDRMST = new DDRMST();
    DDRCCY DDRCCY = new DDRCCY();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    DDCUCBAL DDCUCBAL;
    public void MP(SCCGWA SCCGWA, DDCUCBAL DDCUCBAL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCUCBAL = DDCUCBAL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZUCBAL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_GET_CBAL_AC_PROC();
        if (pgmRtn) return;
    }
    public void B010_GET_CBAL_AC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        DDRMST.PROD_CODE = DDCUCBAL.PROD_CODE;
        T000_STARTBR_DDTMST();
        if (pgmRtn) return;
        T000_READNEXT_DDTMST();
        if (pgmRtn) return;
        while (WS_TABLE_FLG != 'N') {
            T000_READ_DDTCCY_FIRST();
            if (pgmRtn) return;
            if (DDCUCBAL.AC.equalsIgnoreCase(DDRMST.KEY.CUS_AC)) {
                DDRCCY.CURR_BAL = DDCUCBAL.AC_AMT;
                CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
            }
            CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
            if (DDRCCY.CURR_BAL > 0) {
                R000_GET_BALCOTR_PARM();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, DDCSFGAM.FLG);
                if (DDCSFGAM.FLG == 'N' 
                    && DDRCCY.CCY.equalsIgnoreCase("156")) {
                } else {
                    CEP.TRC(SCCGWA, "11111");
                    B040_CHG_CCY_BAL_PROC();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, BPCEX.SELL_AMT);
                    WS_LIMIT_AMT = WS_LIMIT_AMT + BPCEX.SELL_AMT;
                    CEP.TRC(SCCGWA, WS_LIMIT_AMT);
                }
            }
            T000_READNEXT_DDTMST();
            if (pgmRtn) return;
        }
        T000_ENDBR_DDTMST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_LIMIT_AMT);
        CEP.TRC(SCCGWA, DDCSFGAM.TEMP_AMT);
        if (WS_LIMIT_AMT > DDCSFGAM.TEMP_AMT) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_FRG_LOAN_GT_LMT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B040_CHG_CCY_BAL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCEX);
        BPCEX.BUY_CCY = DDRCCY.CCY;
        BPCEX.BUY_AMT = DDRCCY.CURR_BAL;
        BPCEX.SELL_CCY = DDCSFGAM.CCY;
        CEP.TRC(SCCGWA, BPCEX.BUY_CCY);
        CEP.TRC(SCCGWA, BPCEX.SELL_CCY);
        CEP.TRC(SCCGWA, BPCEX.BUY_AMT);
        S000_LINK_BPZSEX();
        if (pgmRtn) return;
    }
    public void R000_GET_BALCOTR_PARM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSFGAM);
        DDCSFGAM.FUNC = '1';
        DDCSFGAM.BNK = "001";
        S000_CALL_DDZSFGAM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCSFGAM.CCY);
        CEP.TRC(SCCGWA, DDCSFGAM.EXR_TYP);
        CEP.TRC(SCCGWA, DDCSFGAM.FLG);
        CEP.TRC(SCCGWA, DDCSFGAM.FRG_LAMT);
        CEP.TRC(SCCGWA, DDCSFGAM.TEMP_AMT);
    }
    public void T000_READ_DDTCCY_FIRST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = DDRMST.KEY.CUS_AC;
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.col = "AC,CCY,CURR_BAL";
        DDTCCY_RD.fst = true;
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
    }
    public void S000_LINK_BPZSEX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-EX", BPCEX);
    }
    public void S000_CALL_DDZSFGAM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-DDZSFGAM", DDCSFGAM, true);
    }
    public void T000_STARTBR_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_BR.rp = new DBParm();
        DDTMST_BR.rp.TableName = "DDTMST";
        DDTMST_BR.rp.col = "CUS_AC";
        DDTMST_BR.rp.where = "CI_TYP < > '1' "
            + "AND PROD_CODE = :DDRMST.PROD_CODE";
        IBS.STARTBR(SCCGWA, DDRMST, this, DDTMST_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_FLG = 'N';
        }
    }
    public void T000_READNEXT_DDTMST() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRMST, this, DDTMST_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_FLG = 'N';
        }
    }
    public void T000_ENDBR_DDTMST() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTMST_BR);
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
