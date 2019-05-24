package com.hisun.CI;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIZBACCK {
    int BAS_CI_NM_LEN;
    int JIBS_tmp_int;
    DBParm CITBAS_RD;
    DBParm CITACR_RD;
    DBParm CITACCK_RD;
    brParm CITACR_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int K_MAX_ROW = 10;
    int K_MAX_COL = 0;
    int K_COL_CNT = 0;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    CIRBAS CIRBAS = new CIRBAS();
    CIRACR CIRACR = new CIRACR();
    CIRACCK CIRACCK = new CIRACCK();
    CICOACCK CICOACCK = new CICOACCK();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICBACCK CICBACCK;
    public void MP(SCCGWA SCCGWA, CICBACCK CICBACCK) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICBACCK = CICBACCK;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZBACCK return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICBACCK.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_BROWSE_ACCK_INF();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (CICBACCK.DATA.CI_NM.trim().length() > 0 
                && CICBACCK.DATA.ID_TYPE.trim().length() > 0 
                && CICBACCK.DATA.ID_NO.trim().length() > 0) {
        } else if (CICBACCK.DATA.CI_NM.trim().length() == 0 
                && CICBACCK.DATA.ID_TYPE.trim().length() == 0 
                && CICBACCK.DATA.ID_NO.trim().length() == 0) {
        } else {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ID_NM_NOT_MATCH, CICBACCK.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_BROWSE_ACCK_INF() throws IOException,SQLException,Exception {
        if (CICBACCK.DATA.AC_NO.trim().length() > 0) {
            B021_BROWSE_BY_AC_NO();
            if (pgmRtn) return;
        } else if (CICBACCK.DATA.CI_NO.trim().length() > 0) {
            B022_BROWSE_BY_CI_NO();
            if (pgmRtn) return;
        } else if (CICBACCK.DATA.CI_NM.trim().length() > 0 
                && CICBACCK.DATA.ID_TYPE.trim().length() > 0 
                && CICBACCK.DATA.ID_NO.trim().length() > 0) {
            B023_BROWSE_BY_IDNM();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, CICBACCK.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B021_BROWSE_BY_AC_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRACR);
        CIRACR.KEY.AGR_NO = CICBACCK.DATA.AC_NO;
        T000_READ_CITACR();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CIRACR.CI_NO;
        T000_READ_CITBAS_EXIST();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRACCK);
        CIRACCK.KEY.AC_NO = CICBACCK.DATA.AC_NO;
        T000_READ_CITACCK();
        if (pgmRtn) return;
        R000_01_OUT_TITLE();
        if (pgmRtn) return;
        if ((CICBACCK.DATA.AC_TYPE == ' ' 
            || CICBACCK.DATA.AC_TYPE == CIRACCK.AC_TYPE) 
            && (CICBACCK.DATA.CHK_FLG == ' ' 
            || CICBACCK.DATA.CHK_FLG == CIRACCK.CHK_FLG) 
            && SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            R000_02_OUT_BRW_DATA();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACCK_INF_NOTFND, CICBACCK.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B022_BROWSE_BY_CI_NO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICBACCK.DATA.CI_NO);
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CICBACCK.DATA.CI_NO;
        T000_READ_CITBAS_EXIST();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRACR);
        CIRACR.CI_NO = CICBACCK.DATA.CI_NO;
        T000_STARTBR_CITACR();
        if (pgmRtn) return;
        T000_READNEXT_CITACR();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "ACR INF NOT FOUND");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACR_INF_NOTFND, CICBACCK.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        R000_01_OUT_TITLE();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && SCCMPAG.FUNC != 'E') {
            IBS.init(SCCGWA, CIRACCK);
            CIRACCK.KEY.AC_NO = CIRACR.KEY.AGR_NO;
            T000_READ_CITACCK();
            if (pgmRtn) return;
            if ((CICBACCK.DATA.AC_TYPE == ' ' 
                || CICBACCK.DATA.AC_TYPE == CIRACCK.AC_TYPE) 
                && (CICBACCK.DATA.CHK_FLG == ' ' 
                || CICBACCK.DATA.CHK_FLG == CIRACCK.CHK_FLG) 
                && SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                R000_02_OUT_BRW_DATA();
                if (pgmRtn) return;
            }
            T000_READNEXT_CITACR();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CIRACR.CI_NO);
        }
        T000_ENDBR_CITACR();
        if (pgmRtn) return;
    }
    public void B023_BROWSE_BY_IDNM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.CI_NM = CICBACCK.DATA.CI_NM;
        BAS_CI_NM_LEN = CIRBAS.CI_NM.length();
        CIRBAS.ID_NO = CICBACCK.DATA.ID_NO;
        CIRBAS.ID_TYPE = CICBACCK.DATA.ID_TYPE;
        T000_READ_CITBAS_IDNM();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRACR);
        CIRACR.CI_NO = CIRBAS.KEY.CI_NO;
        T000_STARTBR_CITACR();
        if (pgmRtn) return;
        T000_READNEXT_CITACR();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "ACR INF NOT FOUND");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACR_INF_NOTFND, CICBACCK.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        R000_01_OUT_TITLE();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && SCCMPAG.FUNC != 'E') {
            CEP.TRC(SCCGWA, CIRACR.KEY.AGR_NO);
            IBS.init(SCCGWA, CIRACCK);
            CIRACCK.KEY.AC_NO = CIRACR.KEY.AGR_NO;
            T000_READ_CITACCK();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
            if ((CICBACCK.DATA.AC_TYPE == ' ' 
                || CICBACCK.DATA.AC_TYPE == CIRACCK.AC_TYPE) 
                && (CICBACCK.DATA.CHK_FLG == ' ' 
                || CICBACCK.DATA.CHK_FLG == CIRACCK.CHK_FLG) 
                && SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                R000_02_OUT_BRW_DATA();
                if (pgmRtn) return;
            }
            T000_READNEXT_CITACR();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITACR();
        if (pgmRtn) return;
    }
    public void R000_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.MAX_COL_NO = (short) K_MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICOACCK);
        CICOACCK.DATA.AC_NO = CIRACR.KEY.AGR_NO;
        CICOACCK.DATA.CI_NO = CIRACR.CI_NO;
        CICOACCK.DATA.ID_TYP = CIRBAS.ID_TYPE;
        CICOACCK.DATA.ID_NO = CIRBAS.ID_NO;
        if (CIRBAS.CI_NM == null) CIRBAS.CI_NM = "";
        JIBS_tmp_int = CIRBAS.CI_NM.length();
        for (int i=0;i<252-JIBS_tmp_int;i++) CIRBAS.CI_NM += " ";
        CICOACCK.DATA.CI_NM = CIRBAS.CI_NM.substring(0, BAS_CI_NM_LEN);
        CICOACCK.DATA.AC_TYPE = CIRACCK.AC_TYPE;
        CICOACCK.DATA.OPEN_DT = CIRACR.OPEN_DT;
        CICOACCK.DATA.CHK_FLG = CIRACCK.CHK_FLG;
        CICOACCK.DATA.CAN_FLG = CIRACCK.CAN_FLG;
        CICOACCK.DATA.TREAT_TP = CIRACCK.TREAT_TP;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, CICOACCK);
        SCCMPAG.DATA_LEN = 385;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void T000_READ_CITBAS_EXIST() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_NOTFND, CICBACCK.RC);
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
    public void T000_READ_CITACR() throws IOException,SQLException,Exception {
        CITACR_RD = new DBParm();
        CITACR_RD.TableName = "CITACR";
        IBS.READ(SCCGWA, CIRACR, CITACR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "ACR INF NOT FOUND");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACR_INF_NOTFND, CICBACCK.RC);
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
    public void T000_READ_CITACCK() throws IOException,SQLException,Exception {
        CITACCK_RD = new DBParm();
        CITACCK_RD.TableName = "CITACCK";
        IBS.READ(SCCGWA, CIRACCK, CITACCK_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0'
            || SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITACCK";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITBAS_IDNM() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        CITBAS_RD.eqWhere = "ID_NO , ID_TYPE , CI_NM";
        CITBAS_RD.where = "SUBSTR ( STSW , 3 , 1 ) = '0' "
            + "AND SUBSTR ( STSW , 23 , 1 ) = '0'";
        IBS.READ(SCCGWA, CIRBAS, this, CITBAS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "CI ID NOT FOUND");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_NOTFND, CICBACCK.RC);
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
    public void T000_STARTBR_CITACR() throws IOException,SQLException,Exception {
        CITACR_BR.rp = new DBParm();
        CITACR_BR.rp.TableName = "CITACR";
        CITACR_BR.rp.eqWhere = "CI_NO";
        IBS.STARTBR(SCCGWA, CIRACR, CITACR_BR);
    }
    public void T000_READNEXT_CITACR() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRACR, this, CITACR_BR);
    }
    public void T000_ENDBR_CITACR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITACR_BR);
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
