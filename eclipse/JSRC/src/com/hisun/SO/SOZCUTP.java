package com.hisun.SO;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCMSG_ERROR_MSG;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCRCWAT;

public class SOZCUTP {
    boolean pgmRtn = false;
    Object CWA_PTR;
    int RESP_CODE = 0;
    String WS_MSGID = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SOCMSG SOCMSG = new SOCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBSPS SCCBSPS = new SCCBSPS();
    SCRBBTL SCRBBTL = new SCRBBTL();
    SCRCWA SCRCWAT = new SCRCWA();
    SCRCWAT SCRPCWA = new SCRCWAT();
    SCCGWA SCCGWA;
    SCRCWAT SCRCWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SOZCUTP return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        T000_READ_UPD_SCTCWA();
        if (pgmRtn) return;
        if (SCRPCWA.SYS_STUS != 'A') {
            WS_MSGID = SOCMSG.SYS_CLS;
            S000_ERROR_PROCESS();
            if (pgmRtn) return;
        }
        if (SCRPCWA.BUSS_MODE == 'O') {
            WS_MSGID = SOCMSG.SO_ERR_BUSS_OPEN;
            S000_ERROR_PROCESS();
            if (pgmRtn) return;
        }
        T000_STARTBR_SCTBBTL();
        if (pgmRtn) return;
        T000_READNEXT_SCTBBTL();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            CEP.TRC(SCCGWA, SCRBBTL.FIN_AC_DATE);
            CEP.TRC(SCCGWA, SCRBBTL.RCV_DATE);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
            if (SCRBBTL.FIN_AC_DATE != SCRBBTL.RCV_DATE 
                && SCRBBTL.RCV_DATE < SCCGWA.COMM_AREA.AC_DATE) {
                T000_ENDBR_SCTBBTL();
                if (pgmRtn) return;
                WS_MSGID = SOCMSG.BTR_NOT;
                S000_ERROR_PROCESS();
                if (pgmRtn) return;
            }
            T000_READNEXT_SCTBBTL();
            if (pgmRtn) return;
        }
        T000_ENDBR_SCTBBTL();
        if (pgmRtn) return;
        SCRBBTL.FIN_AC_DATE = SCRPCWA.AC_DATE_AREA[2-1].AC_DATE;
        T000_DELETE_SCTBBTL();
        if (pgmRtn) return;
        SCRPCWA.AC_DATE_AREA[2-1].AC_DATE = SCRPCWA.AC_DATE_AREA[1-1].AC_DATE;
        SCRPCWA.AC_DATE_AREA[2-1].LAST_AC_DATE = SCRPCWA.AC_DATE_AREA[1-1].LAST_AC_DATE;
        SCRPCWA.AC_DATE_AREA[2-1].NEXT_AC_DATE = SCRPCWA.AC_DATE_AREA[1-1].NEXT_AC_DATE;
        if (SCRPCWA.JRN_IN_USE == '1') {
            SCRPCWA.NEXT_JRN_NO2 = 1;
        } else {
            SCRPCWA.NEXT_JRN_NO1 = 1;
        }
        T000_REWRITE_SCTCWA();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCBSPS);
        SCCBSPS.FUN = 'S';
        S000_CALL_SCZBSPS();
        if (pgmRtn) return;
    }
    public void S000_CALL_SCZBSPS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-S-GET-BSP-INF", SCCBSPS);
    }
    public void S000_ERROR_PROCESS() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID);
        Z_RET();
        if (pgmRtn) return;
    }
    public void S000_SEND_MSG_TO_OP() throws IOException,SQLException,Exception {
    }
    public void T000_STARTBR_SCTBBTL() throws IOException,SQLException,Exception {
        SCTBBTL_BR.rp = new DBParm();
        SCTBBTL_BR.rp.TableName = "SCTBBTL";
        IBS.STARTBR(SCCGWA, SCRBBTL, SCTBBTL_BR);
    }
    public void T000_READNEXT_SCTBBTL() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, SCRBBTL, this, SCTBBTL_BR);
    }
    public void T000_ENDBR_SCTBBTL() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, SCTBBTL_BR);
    }
    public void T000_DELETE_SCTBBTL() throws IOException,SQLException,Exception {
        SCTBBTL_RD = new DBParm();
        SCTBBTL_RD.TableName = "SCTBBTL";
        SCTBBTL_RD.where = "FIN_AC_DATE = :SCRBBTL.FIN_AC_DATE";
        IBS.DELETE(SCCGWA, SCRBBTL, this, SCTBBTL_RD);
    }
    public void T000_READ_UPD_SCTCWA() throws IOException,SQLException,Exception {
        SCRCWAT.KEY.BANK_NO = SCCGWA.COMM_AREA.TR_BANK;
        SCTCWA_RD = new DBParm();
        SCTCWA_RD.TableName = "SCTCWA";
        SCTCWA_RD.upd = true;
        IBS.READ(SCCGWA, SCRCWAT, SCTCWA_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            WS_MSGID = SOCMSG.SO_SYS_ERROR;
            S000_ERROR_PROCESS();
            if (pgmRtn) return;
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCRCWAT);
