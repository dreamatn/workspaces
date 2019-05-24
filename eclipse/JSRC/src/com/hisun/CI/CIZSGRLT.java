package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIZSGRLT {
    int BAS_CI_NM_LEN;
    int JIBS_tmp_int;
    DBParm CITBAS_RD;
    DBParm CITCGRP_RD;
    DBParm CITGRLST_RD;
    DBParm CITGRPM_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CIRBAS CIRBAS = new CIRBAS();
    CIRCGRP CIRCGRP = new CIRCGRP();
    CIRGRPM CIRGRPM = new CIRGRPM();
    CIRGRLST CIRGRLST = new CIRGRLST();
    BPCPARMC BPCPARMC = new BPCPARMC();
    BPCPRMR BPCPRMR = new BPCPRMR();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICSGRLT CICSGRLT;
    public void MP(SCCGWA SCCGWA, CICSGRLT CICSGRLT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICSGRLT = CICSGRLT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZSGRLT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSGRLT.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        if (CICSGRLT.DATA.CI_NO.trim().length() == 0) {
            B020_ADD_GRLST_INF();
            if (pgmRtn) return;
        } else {
            B030_ADD_GRPM_INF();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICSGRLT.DATA.CI_NO);
        CEP.TRC(SCCGWA, CICSGRLT.DATA.ID_TYPE);
        CEP.TRC(SCCGWA, CICSGRLT.DATA.ID_NO);
        CEP.TRC(SCCGWA, CICSGRLT.DATA.CI_NM);
        if (CICSGRLT.DATA.CI_NO.trim().length() == 0) {
            IBS.init(SCCGWA, CIRBAS);
            CIRBAS.ID_TYPE = CICSGRLT.DATA.ID_TYPE;
            CIRBAS.ID_NO = CICSGRLT.DATA.ID_NO;
            CIRBAS.CI_NM = CICSGRLT.DATA.CI_NM;
            BAS_CI_NM_LEN = CIRBAS.CI_NM.length();
            T000_READ_CITBAS_BY_IDNM();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                CICSGRLT.DATA.CI_NO = CIRBAS.KEY.CI_NO;
            }
        } else {
            IBS.init(SCCGWA, CIRBAS);
            CIRBAS.KEY.CI_NO = CICSGRLT.DATA.CI_NO;
            T000_READ_CITBAS_EXIST();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, CICSGRLT.DATA.GRPS_NO);
        IBS.init(SCCGWA, CIRCGRP);
        CIRCGRP.KEY.GRPS_NO = CICSGRLT.DATA.GRPS_NO;
        T000_READ_CITCGRP_EXIST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CIRCGRP.FLG);
        if (CIRCGRP.FLG == '1') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_CANT_DYN_GRPS);
        }
        if (CICSGRLT.DATA.ID_TYPE.trim().length() > 0) {
            IBS.init(SCCGWA, BPCPARMC);
            IBS.init(SCCGWA, BPCPRMR);
            BPCPRMR.FUNC = ' ';
            BPCPRMR.TYP = "PARMC";
            if (BPCPRMR.CD == null) BPCPRMR.CD = "";
            JIBS_tmp_int = BPCPRMR.CD.length();
            for (int i=0;i<40-JIBS_tmp_int;i++) BPCPRMR.CD += " ";
            BPCPRMR.CD = "CIIDO" + BPCPRMR.CD.substring(5);
            if (BPCPRMR.CD == null) BPCPRMR.CD = "";
            JIBS_tmp_int = BPCPRMR.CD.length();
            for (int i=0;i<40-JIBS_tmp_int;i++) BPCPRMR.CD += " ";
            if (CICSGRLT.DATA.ID_TYPE == null) CICSGRLT.DATA.ID_TYPE = "";
            JIBS_tmp_int = CICSGRLT.DATA.ID_TYPE.length();
            for (int i=0;i<5-JIBS_tmp_int;i++) CICSGRLT.DATA.ID_TYPE += " ";
            BPCPRMR.CD = BPCPRMR.CD.substring(0, 6 - 1) + CICSGRLT.DATA.ID_TYPE + BPCPRMR.CD.substring(6 + 5 - 1);
            BPCPRMR.DAT_PTR = BPCPARMC;
            S000_CALL_BPZPRMR();
            if (pgmRtn) return;
        }
    }
    public void B020_ADD_GRLST_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRGRLST);
        CIRGRLST.KEY.ID_TYP = CICSGRLT.DATA.ID_TYPE;
        CIRGRLST.KEY.ID_NO = CICSGRLT.DATA.ID_NO;
        CIRGRLST.KEY.CI_NM = CICSGRLT.DATA.CI_NM;
        CIRGRLST.KEY.GRPS_NO = CICSGRLT.DATA.GRPS_NO;
        T000_READ_CITGRLST_EXIST();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_GRLST_EXIST);
        }
        CIRGRLST.KEY.EFF_DT = CICSGRLT.DATA.EFF_DATE;
        CIRGRLST.KEY.EXP_DT = CICSGRLT.DATA.EXP_DATE;
        CIRGRLST.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRGRLST.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRGRLST.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRGRLST.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRGRLST.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CIRGRLST.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_WRITE_CITGRLST();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.FMT_ID = "CIRGRLST";
        BPCPNHIS.INFO.FMT_ID_LEN = 542;
        BPCPNHIS.INFO.NEW_DAT_PT = CIRGRLST;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B030_ADD_GRPM_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRGRPM);
        CIRGRPM.KEY.ENTY_TYP = '0';
        CIRGRPM.KEY.ENTY_NO = CICSGRLT.DATA.CI_NO;
        CIRGRPM.KEY.GRPS_NO = CICSGRLT.DATA.GRPS_NO;
        T000_READ_CITGRPM_EXIST();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_GRPM_INF_EXIST);
        }
        if (CICSGRLT.DATA.EFF_DATE < CIRCGRP.EFF_DATE) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.EFF_DT_INPUT_ERROR);
        }
        if (CICSGRLT.DATA.EXP_DATE > CIRCGRP.EXP_DATE) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_EXP_DT_INPUT_ERR);
        }
        CIRGRPM.KEY.EFF_DT = CICSGRLT.DATA.EFF_DATE;
        CIRGRPM.KEY.EXP_DT = CICSGRLT.DATA.EXP_DATE;
        CIRGRPM.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRGRPM.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRGRPM.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRGRPM.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRGRPM.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CIRGRPM.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_WRITE_CITGRPM();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.FMT_ID = "CIRGRPM";
        BPCPNHIS.INFO.FMT_ID_LEN = 128;
        BPCPNHIS.INFO.CI_NO = CICSGRLT.DATA.CI_NO;
        BPCPNHIS.INFO.NEW_DAT_PT = CIRGRPM;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPNHIS.RC);
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_NOT_CFM_ID_TYPE);
        }
    }
    public void T000_READ_CITBAS_BY_IDNM() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        CITBAS_RD.eqWhere = "ID_TYPE , ID_NO , CI_NM";
        CITBAS_RD.where = "SUBSTR ( STSW , 3 , 1 ) = '0' "
            + "AND SUBSTR ( STSW , 23 , 1 ) = '0'";
        IBS.READ(SCCGWA, CIRBAS, this, CITBAS_RD);
    }
    public void T000_READ_CITBAS_EXIST() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_NOTFND);
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
    public void T000_READ_CITCGRP_EXIST() throws IOException,SQLException,Exception {
        CITCGRP_RD = new DBParm();
        CITCGRP_RD.TableName = "CITCGRP";
        IBS.READ(SCCGWA, CIRCGRP, CITCGRP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_GRP_NOTFND);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITCGRP";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITGRLST_EXIST() throws IOException,SQLException,Exception {
        CITGRLST_RD = new DBParm();
        CITGRLST_RD.TableName = "CITGRLST";
        CITGRLST_RD.eqWhere = "ID_TYP , ID_NO , CI_NM , GRPS_NO";
        CITGRLST_RD.where = "EXP_DT >= :SCCGWA.COMM_AREA.AC_DATE "
            + "OR EXP_DT = 0";
        IBS.READ(SCCGWA, CIRGRLST, this, CITGRLST_RD);
    }
    public void T000_WRITE_CITGRLST() throws IOException,SQLException,Exception {
        CITGRLST_RD = new DBParm();
        CITGRLST_RD.TableName = "CITGRLST";
        IBS.WRITE(SCCGWA, CIRGRLST, CITGRLST_RD);
    }
    public void T000_READ_CITGRPM_EXIST() throws IOException,SQLException,Exception {
        CITGRPM_RD = new DBParm();
        CITGRPM_RD.TableName = "CITGRPM";
        CITGRPM_RD.eqWhere = "ENTY_TYP , ENTY_NO ,GRPS_NO";
        CITGRPM_RD.where = "EXP_DT >= :SCCGWA.COMM_AREA.AC_DATE "
            + "OR EXP_DT = 0";
        IBS.READ(SCCGWA, CIRGRPM, this, CITGRPM_RD);
    }
    public void T000_WRITE_CITGRPM() throws IOException,SQLException,Exception {
        CITGRPM_RD = new DBParm();
        CITGRPM_RD.TableName = "CITGRPM";
        IBS.WRITE(SCCGWA, CIRGRPM, CITGRPM_RD);
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
