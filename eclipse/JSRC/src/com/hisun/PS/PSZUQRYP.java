package com.hisun.PS;

import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class PSZUQRYP {
    brParm PSTIBLL_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_EXG_BK_NO = "001";
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    int WS_ISS_BR_MSG = 0;
    short WS_NUM = 0;
    char WS_READ_TRAN = ' ';
    char WS_TBL_FLAG = ' ';
    PSZUQRYP_WS_OUT_DATA WS_OUT_DATA = new PSZUQRYP_WS_OUT_DATA();
    PSCMSG_ERROR_MSG PSCMSG_ERROR_MSG = new PSCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    PSRIBLL PSRIBLL = new PSRIBLL();
    PSRPBIN PSRPBIN = new PSRPBIN();
    PSREINF PSREINF = new PSREINF();
    int WS_EXG_B_DT = 0;
    int WS_EXG_E_DT = 0;
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    PSCUQRYP PSCUQRYP;
    public void MP(SCCGWA SCCGWA, PSCUQRYP PSCUQRYP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.PSCUQRYP = PSCUQRYP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "PSZUQRYP return!");
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
        if (PSCUQRYP.EXG_AREA_NO.trim().length() == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_AREA_NO_MST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PSCUQRYP.EXG_B_DT > PSCUQRYP.EXG_E_DT) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_B_E_DT_ER;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B300_MPAG_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.SCR_ROW_CNT = 20;
        SCCMPAG.SCR_COL_CNT = 0;
        SCCMPAG.MAX_COL_NO = 731;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B400_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUT_DATA);
        WS_OUT_DATA.WS_EXG_AREA_NO = PSRIBLL.KEY.EXG_AREA_NO;
        WS_OUT_DATA.WS_OUR_EXG_NO = PSRIBLL.OUR_EXG_NO;
        WS_OUT_DATA.WS_EXG_CCY = PSRIBLL.EXG_CCY;
        WS_OUT_DATA.WS_EXG_REPT_DT = PSRIBLL.KEY.EXG_DT;
        WS_OUT_DATA.WS_EXG_REPT_TMS = PSRIBLL.KEY.EXG_TMS;
        WS_OUT_DATA.WS_TX_JRNNO = PSRIBLL.KEY.EXG_JRN_NO;
        WS_OUT_DATA.WS_OUR_ACNO = PSRIBLL.OUR_ACNO;
        WS_OUT_DATA.WS_OUR_ACNM = PSRIBLL.OUR_ACNM;
        WS_OUT_DATA.WS_OTH_EXG_NO = PSRIBLL.OTH_EXG_NO;
        WS_OUT_DATA.WS_EXG_DC = PSRIBLL.EXG_DC;
        WS_OUT_DATA.WS_CASH_ID = '1';
        WS_OUT_DATA.WS_EXG_VOUCH_CD = PSRIBLL.EXG_VOUCH_CD;
        WS_OUT_DATA.WS_EXG_CERT_NO = PSRIBLL.EXG_CERT_NO;
        WS_OUT_DATA.WS_ISS_BKNO = PSRIBLL.OTH_BKNO;
        WS_OUT_DATA.WS_ISS_AMT = PSRIBLL.EXG_AMT;
        WS_OUT_DATA.WS_EXG_AMT = PSRIBLL.EXG_AMT;
        WS_OUT_DATA.WS_OTH_ACNO = PSRIBLL.OTH_ACNO;
        WS_OUT_DATA.WS_OTH_ACNM = PSRIBLL.OTH_ACNM;
        WS_OUT_DATA.WS_CERT_DT = PSRIBLL.CERT_DT;
        WS_OUT_DATA.WS_EXP_DT = PSRIBLL.EXP_DT;
        WS_OUT_DATA.WS_ISS_BKNO = PSRIBLL.ISS_BKNO;
        WS_OUT_DATA.WS_EXG_REC_STS = PSRIBLL.EXG_REC_STS;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUT_DATA);
        SCCMPAG.DATA_LEN = 731;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B310_OUTPUT_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_TRAN_PSTIBLL();
        if (pgmRtn) return;
        T000_READNEXT_FIRST_PSTIBLL();
        if (pgmRtn) return;
        B300_MPAG_OUTPUT();
        if (pgmRtn) return;
        while (WS_READ_TRAN != 'N') {
            WS_NUM = (short) (WS_NUM + 1);
            B400_OUTPUT_DATA();
            if (pgmRtn) return;
            T000_READNEXT_PSTIBLL();
            if (pgmRtn) return;
        }
        T000_ENDBR_PSTIBLL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_NUM);
    }
    public void T000_STARTBR_TRAN_PSTIBLL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PSRIBLL);
        PSRIBLL.KEY.EXG_BK_NO = PSCUQRYP.BK_NO;
        PSRIBLL.KEY.EXG_AREA_NO = PSCUQRYP.EXG_AREA_NO;
        PSRIBLL.KEY.EXG_TMS = PSCUQRYP.EXG_TMS;
        PSRIBLL.EXG_CCY = PSCUQRYP.EXG_CCY;
        PSRIBLL.EXG_DC = PSCUQRYP.EXG_DC_FLG;
        PSRIBLL.OUR_EXG_NO = PSCUQRYP.OUR_EXG_NO;
        PSRIBLL.OTH_EXG_NO = PSCUQRYP.OTH_EXG_NO;
        PSRIBLL.OUR_ACNO = PSCUQRYP.OUR_ACNO;
        PSRIBLL.OTH_ACNO = PSCUQRYP.OTH_ACNO;
        WS_EXG_B_DT = PSCUQRYP.EXG_B_DT;
        WS_EXG_E_DT = PSCUQRYP.EXG_E_DT;
        PSRIBLL.EXG_TX_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CEP.TRC(SCCGWA, PSRIBLL.KEY.EXG_BK_NO);
        CEP.TRC(SCCGWA, PSRIBLL.KEY.EXG_AREA_NO);
        CEP.TRC(SCCGWA, WS_EXG_B_DT);
        CEP.TRC(SCCGWA, WS_EXG_E_DT);
        CEP.TRC(SCCGWA, PSRIBLL.KEY.EXG_TMS);
        if ((PSCUQRYP.EXG_B_DT != 0 
                && PSCUQRYP.EXG_E_DT != 0) 
                && PSCUQRYP.EXG_TMS != 0 
                && PSCUQRYP.EXG_DC_FLG == ' ' 
                && PSCUQRYP.OUR_ACNO.trim().length() == 0 
                && PSCUQRYP.OTH_ACNO.trim().length() > 0) {
            CEP.TRC(SCCGWA, "2222222222");
            PSTIBLL_BR.rp = new DBParm();
            PSTIBLL_BR.rp.TableName = "PSTIBLL";
            PSTIBLL_BR.rp.where = "EXG_BK_NO = :PSRIBLL.KEY.EXG_BK_NO "
                + "AND EXG_AREA_NO = :PSRIBLL.KEY.EXG_AREA_NO "
                + "AND EXG_DT >= :WS_EXG_B_DT "
                + "AND EXG_DT <= :WS_EXG_E_DT "
                + "AND EXG_TMS = :PSRIBLL.KEY.EXG_TMS "
                + "AND OTH_ACNO = :PSRIBLL.OTH_ACNO";
            PSTIBLL_BR.rp.order = "EXG_BK_NO";
            IBS.STARTBR(SCCGWA, PSRIBLL, this, PSTIBLL_BR);
        } else if ((PSCUQRYP.EXG_B_DT != 0 
                && PSCUQRYP.EXG_E_DT != 0) 
                && PSCUQRYP.EXG_TMS != 0 
                && PSCUQRYP.EXG_DC_FLG == ' ' 
                && PSCUQRYP.OUR_ACNO.trim().length() > 0 
                && PSCUQRYP.OTH_ACNO.trim().length() == 0) {
            CEP.TRC(SCCGWA, "2222222222");
            PSTIBLL_BR.rp = new DBParm();
            PSTIBLL_BR.rp.TableName = "PSTIBLL";
            PSTIBLL_BR.rp.where = "EXG_BK_NO = :PSRIBLL.KEY.EXG_BK_NO "
                + "AND EXG_AREA_NO = :PSRIBLL.KEY.EXG_AREA_NO "
                + "AND EXG_DT >= :WS_EXG_B_DT "
                + "AND EXG_DT <= :WS_EXG_E_DT "
                + "AND EXG_TMS = :PSRIBLL.KEY.EXG_TMS "
                + "AND OUR_ACNO = :PSRIBLL.OUR_ACNO";
            PSTIBLL_BR.rp.order = "EXG_BK_NO";
            IBS.STARTBR(SCCGWA, PSRIBLL, this, PSTIBLL_BR);
        } else if ((PSCUQRYP.EXG_B_DT != 0 
                && PSCUQRYP.EXG_E_DT != 0) 
                && PSCUQRYP.EXG_TMS != 0 
                && PSCUQRYP.EXG_DC_FLG != ' ' 
                && PSCUQRYP.OUR_ACNO.trim().length() == 0 
                && PSCUQRYP.OTH_ACNO.trim().length() == 0) {
            CEP.TRC(SCCGWA, "2222222222");
            PSTIBLL_BR.rp = new DBParm();
            PSTIBLL_BR.rp.TableName = "PSTIBLL";
            PSTIBLL_BR.rp.where = "EXG_BK_NO = :PSRIBLL.KEY.EXG_BK_NO "
                + "AND EXG_AREA_NO = :PSRIBLL.KEY.EXG_AREA_NO "
                + "AND EXG_DT >= :WS_EXG_B_DT "
                + "AND EXG_DT <= :WS_EXG_E_DT "
                + "AND EXG_TMS = :PSRIBLL.KEY.EXG_TMS "
                + "AND EXG_DC = :PSRIBLL.EXG_DC";
            PSTIBLL_BR.rp.order = "EXG_BK_NO";
            IBS.STARTBR(SCCGWA, PSRIBLL, this, PSTIBLL_BR);
        } else if ((PSCUQRYP.EXG_B_DT != 0 
                && PSCUQRYP.EXG_E_DT != 0) 
                && PSCUQRYP.EXG_TMS != 0 
                && PSCUQRYP.EXG_DC_FLG == ' ' 
                && PSCUQRYP.OUR_ACNO.trim().length() == 0 
                && PSCUQRYP.OTH_ACNO.trim().length() == 0) {
            CEP.TRC(SCCGWA, "2222222222");
            PSTIBLL_BR.rp = new DBParm();
            PSTIBLL_BR.rp.TableName = "PSTIBLL";
            PSTIBLL_BR.rp.where = "EXG_BK_NO = :PSRIBLL.KEY.EXG_BK_NO "
                + "AND EXG_AREA_NO = :PSRIBLL.KEY.EXG_AREA_NO "
                + "AND EXG_DT >= :WS_EXG_B_DT "
                + "AND EXG_DT <= :WS_EXG_E_DT "
                + "AND EXG_TMS = :PSRIBLL.KEY.EXG_TMS";
            PSTIBLL_BR.rp.order = "EXG_BK_NO";
            IBS.STARTBR(SCCGWA, PSRIBLL, this, PSTIBLL_BR);
        } else if ((PSCUQRYP.EXG_B_DT != 0 
                && PSCUQRYP.EXG_E_DT != 0) 
                && PSCUQRYP.EXG_TMS != 0 
                && PSCUQRYP.EXG_DC_FLG != ' ' 
                && PSCUQRYP.OUR_ACNO.trim().length() > 0 
                && PSCUQRYP.OTH_ACNO.trim().length() == 0) {
            CEP.TRC(SCCGWA, "2222222222");
            PSTIBLL_BR.rp = new DBParm();
            PSTIBLL_BR.rp.TableName = "PSTIBLL";
            PSTIBLL_BR.rp.where = "EXG_BK_NO = :PSRIBLL.KEY.EXG_BK_NO "
                + "AND EXG_AREA_NO = :PSRIBLL.KEY.EXG_AREA_NO "
                + "AND EXG_DT >= :WS_EXG_B_DT "
                + "AND EXG_DT <= :WS_EXG_E_DT "
                + "AND EXG_TMS = :PSRIBLL.KEY.EXG_TMS "
                + "AND EXG_DC = :PSRIBLL.EXG_DC "
                + "AND EXG_TX_BR = :PSRIBLL.EXG_TX_BR "
                + "AND OUR_ACNO = :PSRIBLL.OUR_ACNO";
            PSTIBLL_BR.rp.order = "EXG_BK_NO";
            IBS.STARTBR(SCCGWA, PSRIBLL, this, PSTIBLL_BR);
        } else if ((PSCUQRYP.EXG_B_DT != 0 
                && PSCUQRYP.EXG_E_DT != 0) 
                && PSCUQRYP.EXG_TMS != 0 
                && PSCUQRYP.EXG_DC_FLG != ' ' 
                && PSCUQRYP.OUR_ACNO.trim().length() == 0 
                && PSCUQRYP.OTH_ACNO.trim().length() > 0) {
            CEP.TRC(SCCGWA, "2222222222");
            PSTIBLL_BR.rp = new DBParm();
            PSTIBLL_BR.rp.TableName = "PSTIBLL";
            PSTIBLL_BR.rp.where = "EXG_BK_NO = :PSRIBLL.KEY.EXG_BK_NO "
                + "AND EXG_AREA_NO = :PSRIBLL.KEY.EXG_AREA_NO "
                + "AND EXG_DT >= :WS_EXG_B_DT "
                + "AND EXG_DT <= :WS_EXG_E_DT "
                + "AND EXG_TMS = :PSRIBLL.KEY.EXG_TMS "
                + "AND EXG_DC = :PSRIBLL.EXG_DC "
                + "AND EXG_TX_BR = :PSRIBLL.EXG_TX_BR "
                + "AND OTH_ACNO = :PSRIBLL.OTH_ACNO";
            PSTIBLL_BR.rp.order = "EXG_BK_NO";
            IBS.STARTBR(SCCGWA, PSRIBLL, this, PSTIBLL_BR);
        } else {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_INPUT_DATA_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "PSTIBLL ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_PSTIBLL() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, PSRIBLL, this, PSTIBLL_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_READ_TRAN = 'Y';
        } else {
            WS_READ_TRAN = 'N';
        }
    }
    public void T000_READNEXT_FIRST_PSTIBLL() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, PSRIBLL, this, PSTIBLL_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_READ_TRAN = 'Y';
        } else {
            WS_READ_TRAN = 'N';
        }
        if (WS_READ_TRAN == 'N') {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_PSTIBLL_NOFUNT_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_PSTIBLL() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, PSTIBLL_BR);
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
