package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIZMJRL {
    DBParm CITBAS_RD;
    DBParm CITACR_RD;
    DBParm CITJRL_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    char WS_ATTR = ' ';
    String WS_CI_NO = " ";
    short WS_I = 0;
    short WS_NUM = 0;
    String WS_PRI_CI_NO = " ";
    char WS_RTN_FLG = ' ';
    char WS_JRL_FLG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    CIRJRL CIRJRL = new CIRJRL();
    CIRACR CIRACR = new CIRACR();
    CIRBAS CIRBAS = new CIRBAS();
    BPCPRMR BPCPRMR = new BPCPRMR();
    short WS_CNT = 0;
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICMJRL CICMJRL;
    public void MP(SCCGWA SCCGWA, CICMJRL CICMJRL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICMJRL = CICMJRL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZMJRL return!");
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
        if (CICMJRL.FUNC == 'A') {
            B020_ADD_PROC();
            if (pgmRtn) return;
        } else if (CICMJRL.FUNC == 'D') {
            B040_DELETE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_INF_INPUT_ERR, CICMJRL.RC);
            CICMJRL.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (CICMJRL.DATA.JRL_CI_NO.trim().length() == 0 
            && CICMJRL.DATA.AC_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_JRL_AC_OR_CI, CICMJRL.RC);
            CICMJRL.RETURN_INFO = 'F';
            Z_RET();
            if (pgmRtn) return;
        }
        if (CICMJRL.DATA.JRL_CI_NO.trim().length() == 0) {
            IBS.init(SCCGWA, CIRACR);
            CIRACR.KEY.AGR_NO = CICMJRL.DATA.AC_NO;
            T000_READ_CITACR();
            if (pgmRtn) return;
            CICMJRL.DATA.JRL_CI_NO = CIRACR.CI_NO;
        }
        for (WS_I = 1; WS_I <= 10 
            && CICMJRL.DATA.JRL_DATA[WS_I-1].CI_NO.trim().length() != 0; WS_I += 1) {
            WS_CI_NO = CICMJRL.DATA.JRL_DATA[WS_I-1].CI_NO;
            R000_CHECK_CINO();
            if (pgmRtn) return;
            WS_NUM = (short) (WS_NUM + 1);
        }
        CEP.TRC(SCCGWA, WS_NUM);
    }
    public void B020_ADD_PROC() throws IOException,SQLException,Exception {
        for (WS_I = 1; WS_I <= 10 
            && CICMJRL.DATA.JRL_DATA[WS_I-1].CI_NO.trim().length() != 0; WS_I += 1) {
            IBS.init(SCCGWA, CIRJRL);
            CIRJRL.KEY.JCI_NO = CICMJRL.DATA.JRL_CI_NO;
            CIRJRL.KEY.HCI_NO = CICMJRL.DATA.JRL_DATA[WS_I-1].CI_NO;
            T000_READ_CITJRL();
            if (pgmRtn) return;
            if (WS_JRL_FLG == 'F') {
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_JRL_INF_EXIST, CICMJRL.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            CIRJRL.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            CIRJRL.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
            CIRJRL.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            CIRJRL.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            CIRJRL.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
            CIRJRL.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            T000_WRITE_CITJRL();
            if (pgmRtn) return;
        }
    }
    public void B040_DELETE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRJRL);
        CIRJRL.KEY.JCI_NO = CICMJRL.DATA.JRL_CI_NO;
        T000_GROUP_CITJRL_CNT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_CNT);
        WS_CNT = (short) (WS_CNT - 2);
        CEP.TRC(SCCGWA, WS_CNT);
        if (WS_NUM > WS_CNT) {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_JRL_NOT_LESS_TWO, CICMJRL.RC);
            CICMJRL.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
        for (WS_I = 1; WS_I <= 10 
            && CICMJRL.DATA.JRL_DATA[WS_I-1].CI_NO.trim().length() != 0; WS_I += 1) {
            IBS.init(SCCGWA, CIRJRL);
            CIRJRL.KEY.JCI_NO = CICMJRL.DATA.JRL_CI_NO;
            CIRJRL.KEY.HCI_NO = CICMJRL.DATA.JRL_DATA[WS_I-1].CI_NO;
            T000_READ_CITJRL_UPD();
            if (pgmRtn) return;
            if (WS_JRL_FLG == 'N') {
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_JRL_INF_NOT_EXIST, CICMJRL.RC);
                CICMJRL.RETURN_INFO = 'N';
                Z_RET();
                if (pgmRtn) return;
            }
            T000_DELETE_CITJRL();
            if (pgmRtn) return;
        }
    }
    public void R000_CHECK_CINO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = WS_CI_NO;
        T000_READ_CITBAS();
        if (pgmRtn) return;
    }
    public void T000_READ_CITBAS() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        CITBAS_RD.col = "CI_NO";
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_IS_NOT_EXIST, CICMJRL.RC);
            CICMJRL.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITACR() throws IOException,SQLException,Exception {
        CITACR_RD = new DBParm();
        CITACR_RD.TableName = "CITACR";
        CITACR_RD.col = "CI_NO";
        IBS.READ(SCCGWA, CIRACR, CITACR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACR_INF_NOTFND, CICMJRL.RC);
            CICMJRL.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITJRL() throws IOException,SQLException,Exception {
        CITJRL_RD = new DBParm();
        CITJRL_RD.TableName = "CITJRL";
        IBS.READ(SCCGWA, CIRJRL, CITJRL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_JRL_FLG = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_JRL_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITJRL";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITJRL_BY_JRL() throws IOException,SQLException,Exception {
        CITJRL_RD = new DBParm();
        CITJRL_RD.TableName = "CITJRL";
        CITJRL_RD.where = "JCI_NO = :CIRJRL.KEY.JCI_NO";
        CITJRL_RD.fst = true;
        IBS.READ(SCCGWA, CIRJRL, this, CITJRL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_JRL_FLG = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_JRL_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITJRL";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_CITJRL() throws IOException,SQLException,Exception {
        CITJRL_RD = new DBParm();
        CITJRL_RD.TableName = "CITJRL";
        IBS.WRITE(SCCGWA, CIRJRL, CITJRL_RD);
    }
    public void T000_READ_CITJRL_UPD() throws IOException,SQLException,Exception {
        CITJRL_RD = new DBParm();
        CITJRL_RD.TableName = "CITJRL";
        CITJRL_RD.upd = true;
        IBS.READ(SCCGWA, CIRJRL, CITJRL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_JRL_FLG = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_JRL_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITJRL";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_DELETE_CITJRL() throws IOException,SQLException,Exception {
        CITJRL_RD = new DBParm();
        CITJRL_RD.TableName = "CITJRL";
        IBS.DELETE(SCCGWA, CIRJRL, CITJRL_RD);
    }
    public void T000_GROUP_CITJRL_CNT() throws IOException,SQLException,Exception {
        CITJRL_RD = new DBParm();
        CITJRL_RD.TableName = "CITJRL";
        CITJRL_RD.set = "WS-CNT=IFNULL(COUNT(*),0)";
        CITJRL_RD.where = "JCI_NO = :CICMJRL.DATA.JRL_CI_NO";
        IBS.GROUP(SCCGWA, CIRJRL, this, CITJRL_RD);
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
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (CICMJRL.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "CICMJRL=");
            CEP.TRC(SCCGWA, CICMJRL);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
