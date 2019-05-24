package com.hisun.PS;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class PSZPQRYP {
    brParm PSTPBIN_BR = new brParm();
    DBParm PSTPBIN_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_EXG_BK_NO = "001";
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    int WS_ISS_BR_MSG = 0;
    short WS_NUM = 0;
    char WS_READ_TRAN = ' ';
    char WS_TBL_FLAG = ' ';
    PSZPQRYP_WS_OUT_DATA WS_OUT_DATA = new PSZPQRYP_WS_OUT_DATA();
    SCCMPAG SCCMPAG = new SCCMPAG();
    PSCMSG_ERROR_MSG PSCMSG_ERROR_MSG = new PSCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCFMT SCCFMT = new SCCFMT();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    PSRPBIN PSRPBIN = new PSRPBIN();
    int WS_EXG_B_DT = 0;
    int WS_EXG_E_DT = 0;
    int WS_CNT = 0;
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    PSCPQRYP PSCPQRYP;
    public void MP(SCCGWA SCCGWA, PSCPQRYP PSCPQRYP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.PSCPQRYP = PSCPQRYP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "PSZPQRYP return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B310_OUTPUT_PROC();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (PSCPQRYP.EXG_AREA_NO.trim().length() == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_AREA_NO_MST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PSCPQRYP.EXG_NO.trim().length() == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_EX_NO_MST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B300_MPAG_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.SCR_ROW_CNT = 20;
        SCCMPAG.SCR_COL_CNT = 0;
        SCCMPAG.MAX_COL_NO = 446;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B310_OUTPUT_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_TRAN_PSTPBIN();
        if (pgmRtn) return;
        B300_MPAG_OUTPUT();
        if (pgmRtn) return;
        T000_READNEXT_PSTPBIN();
        if (pgmRtn) return;
        while (WS_READ_TRAN != 'N' 
            && WS_NUM != 1) {
            WS_NUM = (short) (WS_NUM + 1);
            WS_OUT_DATA.WS_EXG_AREA_NO = PSRPBIN.KEY.EXG_AREA_NO;
            WS_OUT_DATA.WS_EXG_CCY = PSRPBIN.KEY.EXG_CCY;
            WS_OUT_DATA.WS_EXG_NO = PSRPBIN.KEY.EXG_NO;
            WS_OUT_DATA.WS_EXG_EFF_DT = PSRPBIN.EXG_EFF_DT;
            WS_OUT_DATA.WS_EXG_NB = PSRPBIN.EXG_NB;
            WS_OUT_DATA.WS_PBKA_EX_INSNM = PSRPBIN.PBKA_EX_INSNM;
            WS_OUT_DATA.WS_PBKA_CLR_EXN = PSRPBIN.PBKA_CLR_EXN;
            WS_OUT_DATA.WS_PBKA_CLR_EXNM = PSRPBIN.PBKA_CLR_EXNM;
            WS_OUT_DATA.WS_RMK = PSRPBIN.RMK;
            IBS.init(SCCGWA, SCCMPAG);
            SCCMPAG.FUNC = 'D';
            SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUT_DATA);
            SCCMPAG.DATA_LEN = 446;
            B_MPAG();
            if (pgmRtn) return;
            T000_READNEXT_PSTPBIN();
            if (pgmRtn) return;
        }
        T000_ENDBR_PSTPBIN();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_NUM);
    }
    public void T000_STARTBR_TRAN_PSTPBIN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PSRPBIN);
        PSRPBIN.KEY.EXG_AREA_NO = PSCPQRYP.EXG_AREA_NO;
        PSRPBIN.KEY.EXG_NO = PSCPQRYP.EXG_NO;
        PSRPBIN.KEY.EXG_BK_NO = PSCPQRYP.BK_NO;
        CEP.TRC(SCCGWA, PSRPBIN.KEY.EXG_AREA_NO);
        CEP.TRC(SCCGWA, PSRPBIN.KEY.EXG_NO);
        CEP.TRC(SCCGWA, PSRPBIN.KEY.EXG_BK_NO);
        if ((PSCPQRYP.EXG_AREA_NO.trim().length() > 0 
                && PSCPQRYP.EXG_NO.trim().length() > 0) 
                && PSCPQRYP.BK_NO.trim().length() > 0) {
            CEP.TRC(SCCGWA, "11111111");
            PSTPBIN_BR.rp = new DBParm();
            PSTPBIN_BR.rp.TableName = "PSTPBIN";
            PSTPBIN_BR.rp.where = "EXG_BK_NO = :PSRPBIN.KEY.EXG_BK_NO "
                + "AND EXG_AREA_NO = :PSRPBIN.KEY.EXG_AREA_NO "
                + "AND EXG_NO = :PSRPBIN.KEY.EXG_NO";
            PSTPBIN_BR.rp.order = "EXG_BK_NO";
            IBS.STARTBR(SCCGWA, PSRPBIN, this, PSTPBIN_BR);
            PSTPBIN_RD = new DBParm();
            PSTPBIN_RD.TableName = "PSTPBIN";
            PSTPBIN_RD.set = "WS-CNT=COUNT(*)";
            PSTPBIN_RD.where = "EXG_BK_NO = :PSRPBIN.KEY.EXG_BK_NO "
                + "AND EXG_AREA_NO = :PSRPBIN.KEY.EXG_AREA_NO "
                + "AND EXG_NO = :PSRPBIN.KEY.EXG_NO";
            IBS.GROUP(SCCGWA, PSRPBIN, this, PSTPBIN_RD);
            CEP.TRC(SCCGWA, WS_CNT);
        } else {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_INPUT_DATA_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, WS_CNT);
        if (WS_CNT == 0) {
            WS_TBL_FLAG = 'N';
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_PBKA_NOT_FIND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_PSTPBIN() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, PSRPBIN, this, PSTPBIN_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_READ_TRAN = 'Y';
        } else {
            WS_READ_TRAN = 'N';
        }
    }
    public void T000_READNEXT_FIRST_PSTPBIN() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, PSRPBIN, this, PSTPBIN_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_READ_TRAN = 'Y';
        } else {
            WS_READ_TRAN = 'N';
        }
        if (WS_READ_TRAN == 'N') {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_PSTOBLL_NOFUNT_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_PSTPBIN() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, PSTPBIN_BR);
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
