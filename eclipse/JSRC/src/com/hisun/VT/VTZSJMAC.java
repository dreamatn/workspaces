package com.hisun.VT;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCSUBS;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class VTZSJMAC {
    brParm VTTJMAC_BR = new brParm();
    DBParm VTTJMAC_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_P_INQ_ORG_LOW = "BP-P-INQ-ORG-LOW";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY";
    String WS_ERR_MSG = " ";
    short WS_CNT = 0;
    short WS_LEN = 0;
    VTZSJMAC_WS_OUTPUT WS_OUTPUT = new VTZSJMAC_WS_OUTPUT();
    VTZSJMAC_WS_O_OUTPUT WS_O_OUTPUT = new VTZSJMAC_WS_O_OUTPUT();
    char WS_JMAC_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    VTCMSG_ERROR_MSG VTCMSG_ERROR_MSG = new VTCMSG_ERROR_MSG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCSUBS SCCSUBS = new SCCSUBS();
    VTRJMAC VTROJMAC = new VTRJMAC();
    VTRJMAC VTRNJMAC = new VTRJMAC();
    SCCMPAG SCCMPAG = new SCCMPAG();
    VTRJMAC VTRJMAC = new VTRJMAC();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    BPCPQRGC BPCPQRGC = new BPCPQRGC();
    BPCPQORG BPCPQORG = new BPCPQORG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    VTCSJMAC VTCSJMAC;
    public void MP(SCCGWA SCCGWA, VTCSJMAC VTCSJMAC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.VTCSJMAC = VTCSJMAC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "VTZSJMAC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (VTCSJMAC.FUNC == 'B') {
            B020_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else if (VTCSJMAC.FUNC == 'Q') {
            B030_QUERY_PROCESS();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, VTCSJMAC.AGR_NO);
        CEP.TRC(SCCGWA, VTCSJMAC.AC_NO);
        if (VTCSJMAC.AC_NO.trim().length() == 0) {
            WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_MUST_INPUT;
        }
    }
    public void B020_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 112;
        SCCMPAG.SCR_ROW_CNT = 20;
        SCCMPAG.SCR_COL_CNT = 5;
        B_MPAG();
        if (pgmRtn) return;
        IBS.init(SCCGWA, VTRJMAC);
        CEP.TRC(SCCGWA, VTCSJMAC.AC_NO);
        VTRJMAC.KEY.AC = VTCSJMAC.AC_NO;
        CEP.TRC(SCCGWA, VTRJMAC.KEY.AC);
        T000_STARTBR_VTTJMAC();
        if (pgmRtn) return;
        T000_READNEXT_VTTJMAC();
        if (pgmRtn) return;
        while (WS_JMAC_FLG != 'N') {
            if (WS_JMAC_FLG == 'Y') {
                R00_BRW_OUTPUT();
                if (pgmRtn) return;
            }
            T000_READNEXT_VTTJMAC();
            if (pgmRtn) return;
        }
        T000_ENDBR_VTTJMAC();
        if (pgmRtn) return;
    }
    public void B030_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 112;
        SCCMPAG.SCR_ROW_CNT = 20;
        SCCMPAG.SCR_COL_CNT = 5;
        B_MPAG();
        if (pgmRtn) return;
        IBS.init(SCCGWA, VTRJMAC);
        CEP.TRC(SCCGWA, VTCSJMAC.AC_NO);
        VTRJMAC.KEY.AC = VTCSJMAC.AC_NO;
        CEP.TRC(SCCGWA, VTRJMAC.KEY.AC);
        T000_READ_VTTJMAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_JMAC_FLG);
        if (WS_JMAC_FLG == 'Y') {
            R00_OUTPUT();
            if (pgmRtn) return;
        }
    }
    public void R00_BRW_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        IBS.init(SCCGWA, WS_OUTPUT);
        WS_OUTPUT.WS_AC = VTRJMAC.KEY.AC;
        WS_OUTPUT.WS_REF_NO = VTRJMAC.KEY.REF_NO;
        WS_OUTPUT.WS_EFF_DATE = VTRJMAC.KEY.EFF_DATE;
        WS_OUTPUT.WS_EXP_DATE = VTRJMAC.KEY.EXP_DATE;
        WS_OUTPUT.WS_STS = VTRJMAC.STS;
        WS_OUTPUT.WS_YJ_TAX_BAL_JM = VTRJMAC.YJ_TAX_TOT;
        WS_OUTPUT.WS_M_TAX_BAL_JM = VTRJMAC.M_TAX_TOT;
        WS_OUTPUT.WS_SH_TAX_BAL_JM = VTRJMAC.SH_TAX_TOT;
        SCCFMT.FMTID = "VT050";
        SCCFMT.DATA_PTR = WS_OUTPUT;
        SCCFMT.DATA_LEN = 112;
        IBS.FMT(SCCGWA, SCCFMT);
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUTPUT);
        SCCMPAG.DATA_LEN = 112;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R00_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        IBS.init(SCCGWA, WS_O_OUTPUT);
        WS_O_OUTPUT.WS_O_AC = VTRJMAC.KEY.AC;
        WS_O_OUTPUT.WS_O_REF_NO = VTRJMAC.KEY.REF_NO;
        WS_O_OUTPUT.WS_O_EFF_DATE = VTRJMAC.KEY.EFF_DATE;
        WS_O_OUTPUT.WS_O_EXP_DATE = VTRJMAC.KEY.EXP_DATE;
        WS_O_OUTPUT.WS_O_STS = VTRJMAC.STS;
        WS_O_OUTPUT.WS_O_YJ_TAX_BAL_JM = VTRJMAC.YJ_TAX_TOT;
        WS_O_OUTPUT.WS_O_M_TAX_BAL_JM = VTRJMAC.M_TAX_TOT;
        WS_O_OUTPUT.WS_O_SH_TAX_BAL_JM = VTRJMAC.SH_TAX_TOT;
        SCCFMT.FMTID = "VT051";
        SCCFMT.DATA_PTR = WS_O_OUTPUT;
        SCCFMT.DATA_LEN = 112;
        IBS.FMT(SCCGWA, SCCFMT);
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_O_OUTPUT);
        SCCMPAG.DATA_LEN = 112;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_VTTJMAC() throws IOException,SQLException,Exception {
        VTTJMAC_BR.rp = new DBParm();
        VTTJMAC_BR.rp.TableName = "VTTJMAC";
        VTTJMAC_BR.rp.where = "AC = :VTRJMAC.KEY.AC "
            + "AND STS = 'N'";
        IBS.STARTBR(SCCGWA, VTRJMAC, this, VTTJMAC_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_JMAC_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_JMAC_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTJMAC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_VTTJMAC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, VTRJMAC, this, VTTJMAC_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_JMAC_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_JMAC_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTJMAC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_VTTJMAC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, VTTJMAC_BR);
    }
    public void T000_READ_VTTJMAC() throws IOException,SQLException,Exception {
        VTTJMAC_RD = new DBParm();
        VTTJMAC_RD.TableName = "VTTJMAC";
        VTTJMAC_RD.eqWhere = "AC";
        IBS.READ(SCCGWA, VTRJMAC, VTTJMAC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_JMAC_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_JMAC_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "VTTJMAC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG);
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
